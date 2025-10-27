package io.github.dogeiscut.slash_unlimited.common;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandBuildContext;
import io.github.dogeiscut.slash_unlimited.commands.PowerEnchantCommand;
import io.github.dogeiscut.slash_unlimited.commands.UnlimitedCommand;

public final class SlashUnlimitedCommon {
    public static void registerCommonCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context) {
        System.out.println("[SlashUnlimited] Registering commands (Common)...");
        PowerEnchantCommand.register(dispatcher, context);
        UnlimitedCommand.register(dispatcher);
    }
}
