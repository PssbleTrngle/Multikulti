@file:JvmName("RegistrateExtensions")
@file:JvmMultifileClass

package com.possible_triangle.multikulti.registrate

import com.possible_triangle.multikulti.registrate.builder.ParticleBuilder
import com.possible_triangle.multikulti.registrate.platform.service.RegistrateBuilders
import com.tterrag.registrate.AbstractRegistrate
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.particle.SpriteSet
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType

@JvmOverloads
fun <TOptions : ParticleOptions, TType : ParticleType<TOptions>, TParent : AbstractRegistrate<out TParent>> TParent.createParticle(
    name: String = getCurrentName(),
    factory: () -> TType,
    provider: (sprites: SpriteSet) -> ParticleProvider<TOptions>,
): ParticleBuilder<TOptions, TType, TParent> {
    return entry(name) { callback ->
        RegistrateBuilders.INSTANCE.particle(this, this, name, callback, factory, provider)
    }
}

@JvmOverloads
fun <TParent : AbstractRegistrate<out TParent>> TParent.createParticle(
    name: String = getCurrentName(),
    provider: (sprites: SpriteSet) -> ParticleProvider<SimpleParticleType>,
): ParticleBuilder<SimpleParticleType, SimpleParticleType, TParent> {
    return entry(name) { callback ->
        RegistrateBuilders.INSTANCE.particle(this, this, name, callback, provider)
    }
}