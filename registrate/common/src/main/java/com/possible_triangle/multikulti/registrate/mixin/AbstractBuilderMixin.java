package com.possible_triangle.multikulti.registrate.mixin;

import com.tterrag.registrate.builders.AbstractBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractBuilder.class)
public interface AbstractBuilderMixin<T> {

    @Invoker
    T invokeCreateEntry();

}
