package com.possible_triangle.multikulti.registrate.mixin;

import com.tterrag.registrate.AbstractRegistrate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractRegistrate.class)
public interface RegistrateAccessor {

    @Invoker
    String invokeCurrentName();

}
