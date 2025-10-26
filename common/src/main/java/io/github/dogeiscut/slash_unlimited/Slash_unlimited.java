package io.github.dogeiscut.slash_unlimited;

import dev.architectury.event.events.common.CommandRegistrationEvent;
import io.github.dogeiscut.slash_unlimited.commands.PowerEnchantCommand;
import io.github.dogeiscut.slash_unlimited.commands.UnlimitedCommand;
import net.minecraft.commands.CommandBuildContext;

public final class Slash_unlimited {
    public static final String MOD_ID = "slash_unlimited";

    public static void init() {
        // very lazy code
        CommandRegistrationEvent.EVENT.register((dispatcher, context, environment) -> {
            PowerEnchantCommand.register(dispatcher, context);
            UnlimitedCommand.register(dispatcher);

        });
    }
}
