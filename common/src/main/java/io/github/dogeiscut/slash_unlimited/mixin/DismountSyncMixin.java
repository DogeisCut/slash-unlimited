package io.github.dogeiscut.slash_unlimited.mixin;

import net.minecraft.network.protocol.game.ClientboundSetPassengersPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Entity.class)
public class DismountSyncMixin {

    @Inject(method = "removePassenger", at = @At("TAIL"))
    private void syncDismount(Entity passenger, CallbackInfo ci) {
        Entity thisEntity = (Entity) (Object) this;
        if (!(thisEntity.level() instanceof ServerLevel serverLevel)) return;

        List<ServerPlayer> players = serverLevel.getPlayers(player -> true);

        ClientboundSetPassengersPacket packet = new ClientboundSetPassengersPacket(thisEntity);
        for (ServerPlayer player : players) {
            player.connection.send(packet);
        }
    }
}
