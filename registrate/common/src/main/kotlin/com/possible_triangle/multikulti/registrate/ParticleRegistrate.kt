@file:JvmName("RegistrateExtensions")
@file:JvmMultifileClass

package com.possible_triangle.multikulti.registrate

import com.possible_triangle.multikulti.registrate.builder.ParticleBuilder
import com.possible_triangle.multikulti.registrate.platform.service.RegistrateBuilders
import com.tterrag.registrate.AbstractRegistrate
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType

@JvmOverloads
fun <TOptions : ParticleOptions, TType : ParticleType<TOptions>, TParent : AbstractRegistrate<out TParent>> TParent.createParticle(
    name: String = getCurrentName(),
    factory: () -> TType,
): ParticleBuilder<TOptions, TType, TParent> =
    entry(name) { callback ->
        RegistrateBuilders.INSTANCE.particle(this, this, name, callback, factory)
    }

@JvmOverloads
fun <TParent : AbstractRegistrate<out TParent>> TParent.createParticle(
    name: String = getCurrentName(),
): ParticleBuilder<SimpleParticleType, SimpleParticleType, TParent> =
    entry(name) { callback ->
        RegistrateBuilders.INSTANCE.particle(this, this, name, callback)
    }
