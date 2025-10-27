package io.github.dogeiscut.slash_unlimited.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.commands.data.EntityDataAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(EntityDataAccessor.class)
public abstract class EntityDataAccessorMixin {
    @Inject(method = "setData", at = @At("HEAD"), cancellable = true)
    private void allowPlayerDataModification(CompoundTag compoundTag, CallbackInfo callbackInfo) {
        EntityDataAccessor self = (EntityDataAccessor) (Object) this;
        Entity entity = ((EntityDataAccessorAccessor) self).getEntity();

        if (entity instanceof Player) {
            UUID entityUUID = entity.getUUID();
            entity.load(compoundTag);
            entity.setUUID(entityUUID);
            callbackInfo.cancel();
        }
    }
}
