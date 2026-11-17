package com.possible_triangle.multikulti.registrate.mixin;

import com.tterrag.registrate.AbstractRegistrate;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractRegistrate.class)
public interface RegistrateAccessor {

    @Invoker
    String invokeCurrentName();

    @Accessor
    @Nullable
    ResourceKey<CreativeModeTab> getDefaultCreativeModeTab();

}
