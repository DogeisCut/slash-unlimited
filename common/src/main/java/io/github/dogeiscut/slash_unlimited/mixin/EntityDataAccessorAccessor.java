package io.github.dogeiscut.slash_unlimited.mixin;

import net.minecraft.server.commands.data.EntityDataAccessor;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityDataAccessor.class)
public interface EntityDataAccessorAccessor {
    @Accessor("entity")
    Entity getEntity();
}
