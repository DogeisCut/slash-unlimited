package io.github.dogeiscut.slash_unlimited.fabric;

import io.github.dogeiscut.slash_unlimited.Slash_unlimited;
import net.fabricmc.api.ModInitializer;

public final class Slash_unlimitedFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        Slash_unlimited.init();
    }
}
