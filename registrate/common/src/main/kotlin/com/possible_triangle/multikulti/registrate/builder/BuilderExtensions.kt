package com.possible_triangle.multikulti.registrate.builder

import com.tterrag.registrate.builders.AbstractBuilder
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation

val <T> AbstractBuilder<T, *, *, *>.key
    get(): ResourceKey<T> = ResourceKey.create(
        registryKey,
        ResourceLocation(owner.modid, name)
    )