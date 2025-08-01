package com.possible_triangle.multikulti.registrate

import com.possible_triangle.multikulti.registrate.builder.dataDriven
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.builders.NoConfigBuilder
import com.tterrag.registrate.util.nullness.NonNullSupplier
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.particle.SpriteSet
import net.minecraft.core.Registry
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.resources.ResourceKey
import com.possible_triangle.multikulti.registrate.painting as createPainting
import com.possible_triangle.multikulti.registrate.sound as createSound

open class MultikultiRegistrate<TRegistrate : MultikultiRegistrate<TRegistrate>>(modId: String) :
    AbstractRegistrate<TRegistrate>(modId) {

    @JvmOverloads
    fun sound(name: String = currentName()) = createSound(name)

    @JvmOverloads
    fun painting(name: String = currentName()) = createPainting(name)

    @JvmOverloads
    fun <TOptions : ParticleOptions, TType : ParticleType<TOptions>> particle(
        name: String = currentName(),
        factory: () -> TType,
        provider: (sprites: SpriteSet) -> ParticleProvider<TOptions>,
    ) = createParticle(name, factory, provider)

    @JvmOverloads
    fun particle(
        name: String = currentName(),
        provider: (sprites: SpriteSet) -> ParticleProvider<SimpleParticleType>,
    ) = createParticle(name, provider)

    fun <R, T : R> dataDriven(
        name: String,
        registry: ResourceKey<Registry<R>>,
        factory: NonNullSupplier<T>
    ): NoConfigBuilder<R, T, TRegistrate> =
        generic(name, registry, factory).dataDriven()

    fun <R, T : R> dataDriven(
        registry: ResourceKey<Registry<R>>,
        factory: NonNullSupplier<T>
    ) = dataDriven(currentName(), registry, factory)

}
