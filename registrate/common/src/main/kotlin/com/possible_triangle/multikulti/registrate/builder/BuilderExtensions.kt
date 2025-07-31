package com.possible_triangle.multikulti.registrate.builder

import com.possible_triangle.multikulti.registrate.mixin.AbstractBuilderMixin
import com.tterrag.registrate.builders.AbstractBuilder
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation

val <T> AbstractBuilder<T, *, *, *>.key
    get(): ResourceKey<T> = ResourceKey.create(
        registryKey,
        ResourceLocation.fromNamespaceAndPath(owner.modid, name)
    )

@Suppress("UNCHECKED_CAST")
fun <T, R : T, P, S : AbstractBuilder<T, R, P, S>> S.dataDriven() = apply {
    val accessor = this as AbstractBuilderMixin<T>
    owner.dataGenInitializer.add(registryKey as ResourceKey<Registry<T>>) { context ->
        context.register(key, accessor.invokeCreateEntry()!!)
    }
}