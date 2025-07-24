package com.possible_triangle.multikulti.registrate.platform.service

import com.possible_triangle.multikulti.registrate.builder.FabricPaintingBuilder
import com.possible_triangle.multikulti.registrate.builder.FabricParticleBuilder
import com.possible_triangle.multikulti.registrate.builder.FabricSoundBuilder
import com.possible_triangle.multikulti.registrate.provider.RegistratePaintingTagsProvider
import com.possible_triangle.multikulti.registrate.provider.RegistrateParticleProvider
import com.possible_triangle.multikulti.registrate.provider.RegistrateSoundsProvider
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.builders.BuilderCallback
import com.tterrag.registrate.providers.ProviderType
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.particle.SpriteSet
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType

class FabricRegistrateBuilders : RegistrateBuilders {

    companion object {
        val SOUNDS: ProviderType<RegistrateSoundsProvider> = ProviderType.register("sounds") { owner, context ->
            RegistrateSoundsProvider(owner, context.output, context.helper)
        }

        val PARTICLES: ProviderType<RegistrateParticleProvider> = ProviderType.register("sounds") { owner, context ->
            RegistrateParticleProvider(owner, context.output, context.helper)
        }

        val PAINTING_TAGS: ProviderType<RegistratePaintingTagsProvider> =
            ProviderType.register("painting_tags") { owner, context ->
                RegistratePaintingTagsProvider(owner, context.output, context.registriesLookup)
            }
    }

    override fun <TParent : Any> sound(
        owner: AbstractRegistrate<*>,
        parent: TParent,
        name: String,
        callback: BuilderCallback
    ) = FabricSoundBuilder(owner, parent, name, callback)

    override fun <TOptions : ParticleOptions, TType : ParticleType<TOptions>, TParent : Any> particle(
        owner: AbstractRegistrate<*>,
        parent: TParent,
        name: String,
        callback: BuilderCallback,
        factory: () -> TType,
        provider: (sprites: SpriteSet) -> ParticleProvider<TOptions>,
    ) = FabricParticleBuilder(owner, parent, name, callback, factory, provider)

    override fun <TParent : Any> painting(
        owner: AbstractRegistrate<*>,
        parent: TParent,
        name: String,
        callback: BuilderCallback
    ) = FabricPaintingBuilder(owner, parent, name, callback)

}