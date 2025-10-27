package io.github.dogeiscut.slash_unlimited.fabric;

import io.github.dogeiscut.slash_unlimited.SlashUnlimited;
import io.github.dogeiscut.slash_unlimited.commands.PowerEnchantCommand;
import io.github.dogeiscut.slash_unlimited.commands.UnlimitedCommand;
import io.github.dogeiscut.slash_unlimited.common.SlashUnlimitedCommon;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public final class SlashUnlimitedFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        SlashUnlimited.init();

        CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, environment) -> {
            System.out.println("[SlashUnlimited] Registering commands (Fabric)...");
            SlashUnlimitedCommon.registerCommonCommands(dispatcher, buildContext);
        });
    }
}
