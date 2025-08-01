package com.possible_triangle.multikulti.registrate.platform.service

import com.possible_triangle.multikulti.registrate.builder.FabricPaintingBuilder
import com.possible_triangle.multikulti.registrate.builder.FabricParticleBuilder
import com.possible_triangle.multikulti.registrate.builder.FabricSoundBuilder
import com.possible_triangle.multikulti.registrate.platform.ValidationContext
import com.possible_triangle.multikulti.registrate.provider.RegistratePaintingTagsProvider
import com.possible_triangle.multikulti.registrate.provider.RegistrateParticleProvider
import com.possible_triangle.multikulti.registrate.provider.RegistrateSoundsProvider
import com.possible_triangle.multikulti.registrate.provider.RegistrateValidationProvider
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.builders.Builder
import com.tterrag.registrate.builders.BuilderCallback
import com.tterrag.registrate.providers.ProviderType
import com.tterrag.registrate.providers.RegistrateTagsProvider
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.particle.SpriteSet
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.core.registries.Registries
import net.minecraft.world.entity.decoration.PaintingVariant

class FabricRegistrateBuilders : RegistrateBuilders {

    companion object {
        @JvmStatic
        val SOUNDS: ProviderType<RegistrateSoundsProvider> = ProviderType.register("sounds") { owner, context ->
            RegistrateSoundsProvider(owner, context.output, context.helper)
        }

        @JvmStatic
        val PARTICLES: ProviderType<RegistrateParticleProvider> = ProviderType.register("particles") { owner, context ->
            RegistrateParticleProvider(owner, context.output, context.helper)
        }

        @JvmStatic
        val PAINTING_TAGS: ProviderType<RegistrateTagsProvider.IntrinsicImpl<PaintingVariant>> =
            ProviderType.register("tags/painting") { owner, context ->
                RegistratePaintingTagsProvider(owner, context.output, context.registriesLookup)
            }

        @JvmStatic
        val VALIDATION: ProviderType<RegistrateValidationProvider> = ProviderType.register("validation") { owner, context ->
            RegistrateValidationProvider(owner, context.helper)
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

    override fun <TParent : Any> particle(
        owner: AbstractRegistrate<*>,
        parent: TParent,
        name: String,
        callback: BuilderCallback,
        provider: (SpriteSet) -> ParticleProvider<SimpleParticleType>
    ) = particle(owner, parent, name, callback, FabricParticleTypes::simple, provider)

    override fun <TParent : Any> painting(
        owner: AbstractRegistrate<*>,
        parent: TParent,
        name: String,
        callback: BuilderCallback
    ) = FabricPaintingBuilder(owner, parent, name, callback)

    override fun validate(
        builder: Builder<*, *, *, *>,
        block: ValidationContext.() -> Unit
    ) {
        builder.setData(VALIDATION) { context, provider ->
            provider.block()
        }
    }
}