package com.possible_triangle.multikulti.registrate.platform.service

import com.possible_triangle.multikulti.registrate.builder.FabricPaintingBuilder
import com.possible_triangle.multikulti.registrate.builder.FabricParticleBuilder
import com.possible_triangle.multikulti.registrate.builder.FabricSoundBuilder
import com.possible_triangle.multikulti.registrate.provider.RegistratePaintingVariantProvider
import com.possible_triangle.multikulti.registrate.provider.RegistrateParticleProvider
import com.possible_triangle.multikulti.registrate.provider.RegistrateSoundsProvider
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.builders.BuilderCallback
import com.tterrag.registrate.providers.ProviderType
import com.tterrag.registrate.providers.RegistrateTagsProvider
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.particle.SpriteSet
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.registries.Registries
import net.minecraft.world.entity.decoration.PaintingVariant

class FabricRegistrateBuilders : RegistrateBuilders {

    companion object {
        val SOUNDS: ProviderType<RegistrateSoundsProvider> = ProviderType.registerProvider("sounds") { context ->
            RegistrateSoundsProvider(context.parent, context.output, context.fileHelper)
        }

        val PARTICLES: ProviderType<RegistrateParticleProvider> = ProviderType.registerProvider("particles") { context ->
            RegistrateParticleProvider(context.parent, context.output, context.fileHelper)
        }

        val PAINTING_TAGS: ProviderType<RegistrateTagsProvider.IntrinsicImpl<PaintingVariant>> = ProviderType
            .registerIntrinsicTag("Painting Tags", "tags/painting", Registries.PAINTING_VARIANT, null)

        val PAINTING_VARIANTS: ProviderType<RegistratePaintingVariantProvider> =
            ProviderType.registerProvider("Painting Variants") { context ->
                RegistratePaintingVariantProvider(context.parent, context.output, context.fileHelper)
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