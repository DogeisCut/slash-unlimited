package io.github.dogeiscut.slash_unlimited.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.ResourceArgument;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.Collection;

public class PowerEnchantCommand {
    private static final DynamicCommandExceptionType ERROR_NOT_LIVING_ENTITY =
            new DynamicCommandExceptionType(name -> Component.translatableEscape("commands.enchant.failed.entity", name));
    private static final DynamicCommandExceptionType ERROR_NO_ITEM =
            new DynamicCommandExceptionType(name -> Component.translatableEscape("commands.enchant.failed.itemless", name));
    private static final SimpleCommandExceptionType ERROR_NOTHING_HAPPENED =
            new SimpleCommandExceptionType(Component.translatable("commands.enchant.failed"));

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context) {
        dispatcher.register(
                Commands.literal("powerenchant")
                        .requires(source -> source.hasPermission(2))
                        .then(
                                Commands.argument("targets", EntityArgument.entities())
                                        .then(
                                                ((RequiredArgumentBuilder<CommandSourceStack, ?>) Commands.argument(
                                                        "enchantment",
                                                        ResourceArgument.resource(context, Registries.ENCHANTMENT)
                                                ))
                                                        .executes(ctx -> enchant(
                                                                ctx.getSource(),
                                                                EntityArgument.getEntities(ctx, "targets"),
                                                                ResourceArgument.getEnchantment(ctx, "enchantment"),
                                                                1
                                                        ))
                                                        .then(
                                                                Commands.argument("level", IntegerArgumentType.integer(0))
                                                                        .executes(ctx -> enchant(
                                                                                ctx.getSource(),
                                                                                EntityArgument.getEntities(ctx, "targets"),
                                                                                ResourceArgument.getEnchantment(ctx, "enchantment"),
                                                                                IntegerArgumentType.getInteger(ctx, "level")
                                                                        ))
                                                        )
                                        )
                        )
        );
    }

    private static int enchant(CommandSourceStack source, Collection<? extends Entity> targets, Holder<Enchantment> enchantHolder, int level)
            throws CommandSyntaxException {

        Enchantment enchantment = enchantHolder.value();
        int successCount = 0;

        for (Entity entity : targets) {
            if (!(entity instanceof LivingEntity living)) {
                if (targets.size() == 1) throw ERROR_NOT_LIVING_ENTITY.create(entity.getName().getString());
                continue;
            }

            ItemStack itemStack = living.getMainHandItem();
            if (itemStack.isEmpty()) {
                if (targets.size() == 1) throw ERROR_NO_ITEM.create(living.getName().getString());
                continue;
            }

            itemStack.enchant(enchantHolder, level);
            successCount++;
        }

        if (successCount == 0) throw ERROR_NOTHING_HAPPENED.create();

        if (targets.size() == 1) {
            source.sendSuccess(
                    () -> Component.translatable(
                            "commands.enchant.success.single",
                            Enchantment.getFullname(enchantHolder, level),
                            targets.iterator().next().getDisplayName()
                    ),
                    true
            );
        } else {
            source.sendSuccess(
                    () -> Component.translatable(
                            "commands.enchant.success.multiple",
                            Enchantment.getFullname(enchantHolder, level),
                            targets.size()
                    ),
                    true
            );
        }

        return successCount;
    }
}
