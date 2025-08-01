package com.possible_triangle.multikulti.registrate.platform.service

import com.possible_triangle.multikulti.registrate.builder.ForgePaintingBuilder
import com.possible_triangle.multikulti.registrate.builder.ForgeParticleBuilder
import com.possible_triangle.multikulti.registrate.builder.ForgeSoundBuilder
import com.possible_triangle.multikulti.registrate.builder.ParticleBuilder
import com.possible_triangle.multikulti.registrate.platform.ValidationContext
import com.possible_triangle.multikulti.registrate.provider.RegistrateParticleProvider
import com.possible_triangle.multikulti.registrate.provider.RegistrateSoundsProvider
import com.possible_triangle.multikulti.registrate.provider.RegistrateValidationProvider
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.builders.Builder
import com.tterrag.registrate.builders.BuilderCallback
import com.tterrag.registrate.providers.DataProviderInitializer
import com.tterrag.registrate.providers.ProviderType
import com.tterrag.registrate.providers.RegistrateTagsProvider
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.particle.SpriteSet
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.core.registries.Registries
import net.minecraft.world.entity.decoration.PaintingVariant

class ForgeRegistrateBuilders : RegistrateBuilders {

    companion object {
        @JvmStatic
        val SOUNDS: ProviderType<RegistrateSoundsProvider> = ProviderType.register("sounds") { owner, context ->
            RegistrateSoundsProvider(owner, context.generator.packOutput, context.existingFileHelper)
        }

        @JvmStatic
        val PARTICLES: ProviderType<RegistrateParticleProvider> = ProviderType.register("particles") { owner, context ->
            RegistrateParticleProvider(owner, context.generator.packOutput, context.existingFileHelper)
        }

        @JvmStatic
        val PAINTING_TAGS: ProviderType<RegistrateTagsProvider.IntrinsicImpl<PaintingVariant>> = ProviderType
            .registerIntrinsicTag("Painting Tags", "tags/painting", Registries.PAINTING_VARIANT, null)

        @JvmStatic
        val VALIDATION: ProviderType<RegistrateValidationProvider> = ProviderType.registerProvider("validation") {
            RegistrateValidationProvider(it.parent, it.fileHelper)
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
    ) = ForgeParticleBuilder(owner, parent, name, callback, factory)

    override fun <TParent : Any> particle(
        owner: AbstractRegistrate<*>,
        parent: TParent,
        name: String,
        callback: BuilderCallback,
    ) = particle(owner, parent, name, callback) { SimpleParticleType(false) }

    override fun <TParent : Any> painting(
        owner: AbstractRegistrate<*>,
        parent: TParent,
        name: String,
        callback: BuilderCallback
    ) = ForgePaintingBuilder(owner, parent, name, callback)

    override fun registerDependencies(initializer: DataProviderInitializer) {
        initializer.addDependency(PAINTING_TAGS, ProviderType.DYNAMIC)
        initializer.addDependency(VALIDATION, ProviderType.DYNAMIC)
    }

    override fun validate(
        builder: Builder<*, *, *, *>,
        block: ValidationContext.() -> Unit
    ) {
        builder.setData(VALIDATION) { context, provider ->
            provider.block()
        }
    }
}