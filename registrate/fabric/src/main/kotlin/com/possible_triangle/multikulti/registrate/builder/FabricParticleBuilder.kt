package com.possible_triangle.multikulti.registrate.builder

import com.possible_triangle.multikulti.registrate.platform.service.FabricRegistrateBuilders
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.builders.BuilderCallback
import com.tterrag.registrate.fabric.EnvExecutor
import net.fabricmc.api.EnvType
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.particle.SpriteSet
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType

class FabricParticleBuilder<TOptions : ParticleOptions, TType : ParticleType<TOptions>, TParent : Any>(
    owner: AbstractRegistrate<*>,
    parent: TParent,
    name: String,
    callback: BuilderCallback,
    factory: () -> TType,
    private val provider: (sprites: SpriteSet) -> ParticleProvider<TOptions>
) : ParticleBuilder<TOptions, TType, TParent>(owner, parent, name, callback, factory) {

    init {
        EnvExecutor.runWhenOn(EnvType.CLIENT) { Runnable(this::registerProvider) }

        setData(FabricRegistrateBuilders.PARTICLES) { context, provider ->
            provider.spriteSet(context.id, sprites)
        }
    }

    private fun registerProvider() {
        onRegister {
            ParticleFactoryRegistry.getInstance().register(entry, provider)
        }
    }

}