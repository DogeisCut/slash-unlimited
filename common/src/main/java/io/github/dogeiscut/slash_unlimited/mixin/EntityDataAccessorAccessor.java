package io.github.dogeiscut.slash_unlimited.mixin;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.server.commands.data.EntityDataAccessor;

@Mixin(EntityDataAccessor.class)
public interface EntityDataAccessorAccessor {
    @Accessor("entity")
    Entity getEntity();
}
