package io.github.dogeiscut.slash_unlimited.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class UnlimitedCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        //TODO: some sort of automatic detection of "power" commands as well as some sort of automatic description system
        //TODO: something like /unlimited list or /unlimited <command> for extra detail.
        dispatcher.register(
                Commands.literal("unlimited")
                        .requires(source -> source.hasPermission(0))
                        .executes(context -> {
                            CommandSourceStack source = context.getSource();

                            source.sendSuccess(() -> Component.literal("§6yes this command exists (v1.0.0)"), false);
                            source.sendSuccess(() -> Component.literal(""), false);

                            source.sendSuccess(() -> Component.literal("§7Removed restrictions:"), false);
                            source.sendSuccess(() -> Component.literal(" - §f/data merge works on players now."), false);
                            source.sendSuccess(() -> Component.literal(" - §f/enchant restrictions removed via /powerenchant."), false);
                            source.sendSuccess(() -> Component.literal(" - §f/ride can now mount players."), false);

                            source.sendSuccess(() -> Component.literal(""), false);

                            source.sendSuccess(() -> Component.literal("§7New commands:"), false);
                            source.sendSuccess(() -> Component.literal(" - §b/powerenchant§f - Works like /enchant but ignores level and compatibility limits."), false);

                            source.sendSuccess(() -> Component.literal(""), false);
                            source.sendSuccess(() -> Component.literal("§aHave fun :3"), false);

                            return 1;
                        })
        );
    }
}
