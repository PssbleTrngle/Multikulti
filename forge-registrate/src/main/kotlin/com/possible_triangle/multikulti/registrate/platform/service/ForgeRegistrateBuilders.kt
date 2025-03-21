package com.possible_triangle.multikulti.registrate.platform.service

import com.possible_triangle.multikulti.registrate.builder.ForgeParticleBuilder
import com.possible_triangle.multikulti.registrate.builder.ForgeSoundBuilder
import com.possible_triangle.multikulti.registrate.provider.RegistrateParticleProvider
import com.possible_triangle.multikulti.registrate.provider.RegistrateSoundsProvider
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.builders.BuilderCallback
import com.tterrag.registrate.providers.ProviderType
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.particle.SpriteSet
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType

class ForgeRegistrateBuilders : RegistrateBuilders {

    companion object {
        val SOUNDS: ProviderType<RegistrateSoundsProvider> = ProviderType.register("sounds") { owner, context ->
            RegistrateSoundsProvider(owner, context.generator.packOutput, context.existingFileHelper)
        }

        val PARTICLES: ProviderType<RegistrateParticleProvider> = ProviderType.register("particles") { owner, context ->
            RegistrateParticleProvider(owner, context.generator.packOutput, context.existingFileHelper)
        }
    }

    override fun <TParent : Any> sound(
        owner: AbstractRegistrate<*>,
        parent: TParent,
        name: String,
        callback: BuilderCallback
    ) = ForgeSoundBuilder(owner, parent, name, callback)

    override fun <TOptions : ParticleOptions, TType : ParticleType<TOptions>, TParent : Any> particle(
        owner: AbstractRegistrate<*>,
        parent: TParent,
        name: String,
        callback: BuilderCallback,
        factory: () -> TType,
        provider: (sprites: SpriteSet) -> ParticleProvider<TOptions>,
    ) = ForgeParticleBuilder(owner, parent, name, callback, factory, provider)
}