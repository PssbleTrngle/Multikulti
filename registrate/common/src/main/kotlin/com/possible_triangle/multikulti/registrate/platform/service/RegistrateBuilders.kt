package com.possible_triangle.multikulti.registrate.platform.service

import com.possible_triangle.multikulti.platform.Services
import com.possible_triangle.multikulti.registrate.builder.PaintingBuilder
import com.possible_triangle.multikulti.registrate.builder.ParticleBuilder
import com.possible_triangle.multikulti.registrate.builder.SoundBuilder
import com.possible_triangle.multikulti.registrate.platform.ValidationContext
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.builders.Builder
import com.tterrag.registrate.builders.BuilderCallback
import com.tterrag.registrate.providers.DataProviderInitializer
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.particle.SpriteSet
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType

interface RegistrateBuilders {

    companion object {
        @JvmField
        val INSTANCE = Services.load(RegistrateBuilders::class.java)
    }

    fun <TParent : Any> sound(
        owner: AbstractRegistrate<*>,
        parent: TParent,
        name: String,
        callback: BuilderCallback
    ): SoundBuilder<TParent>

    fun <TOptions : ParticleOptions, TType : ParticleType<TOptions>, TParent : Any> particle(
        owner: AbstractRegistrate<*>,
        parent: TParent,
        name: String,
        callback: BuilderCallback,
        factory: () -> TType,
    ): ParticleBuilder<TOptions, TType, TParent>

    fun <TParent : Any> particle(
        owner: AbstractRegistrate<*>,
        parent: TParent,
        name: String,
        callback: BuilderCallback,
    ): ParticleBuilder<SimpleParticleType, SimpleParticleType, TParent>

    fun <TParent : Any> painting(
        owner: AbstractRegistrate<*>,
        parent: TParent,
        name: String,
        callback: BuilderCallback
    ): PaintingBuilder<TParent>

    fun registerDependencies(initializer: DataProviderInitializer)

    fun validate(builder: Builder<*, *, *, *>, block: ValidationContext.() -> Unit)

}