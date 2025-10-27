package io.github.dogeiscut.slash_unlimited.mixin;

import net.minecraft.network.protocol.game.ClientboundSetPassengersPacket;
import net.minecraft.server.commands.RideCommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RideCommand.class)
public class RideCommandMixin {
    @Redirect(
            method = "mount",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;getType()Lnet/minecraft/world/entity/EntityType;"
            )
    )
    private static EntityType<?> allowPlayerAsVehicle(Entity instance) {
        return instance.getType() == EntityType.PLAYER ? EntityType.PIG : instance.getType();
    }

    @Inject(method = "mount", at = @At("RETURN"))
    private static void syncPassengersAfterRide(
            net.minecraft.commands.CommandSourceStack source,
            Entity rider,
            Entity vehicle,
            CallbackInfoReturnable<Integer> cir
    ) {
        if (!rider.isPassengerOfSameVehicle(vehicle)) return;
        if (!(vehicle.level() instanceof ServerLevel level)) return;
        ClientboundSetPassengersPacket packet = new ClientboundSetPassengersPacket(vehicle);
        for (ServerPlayer player : level.players()) {
            player.connection.send(packet);
        }
    }
}
