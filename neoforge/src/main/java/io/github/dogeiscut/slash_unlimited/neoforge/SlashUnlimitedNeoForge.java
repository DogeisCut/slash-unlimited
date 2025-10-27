package io.github.dogeiscut.slash_unlimited.neoforge;

import io.github.dogeiscut.slash_unlimited.SlashUnlimited;
import io.github.dogeiscut.slash_unlimited.common.SlashUnlimitedCommon;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@Mod(SlashUnlimited.MOD_ID)
@EventBusSubscriber(modid = SlashUnlimited.MOD_ID)
public final class SlashUnlimitedNeoForge {
    public SlashUnlimitedNeoForge() {
        SlashUnlimited.init();
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        System.out.println("[SlashUnlimited] Registering commands (NeoForge)...");
        SlashUnlimitedCommon.registerCommonCommands(event.getDispatcher(), event.getBuildContext());
    }
}
