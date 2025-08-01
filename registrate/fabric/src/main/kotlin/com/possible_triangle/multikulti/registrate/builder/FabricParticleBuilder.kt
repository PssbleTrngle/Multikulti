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
) : ParticleBuilder<TOptions, TType, TParent>(owner, parent, name, callback, factory) {

    init {
        setData(FabricRegistrateBuilders.PARTICLES) { context, provider ->
            provider.spriteSet(context.id, sprites)
        }
    }

    override fun provider(supplier: () -> (SpriteSet) -> ParticleProvider<TOptions>) = apply {
        EnvExecutor.runWhenOn(EnvType.CLIENT) {
            Runnable {
                onRegister {
                    ParticleFactoryRegistry.getInstance().register(it, supplier())
                }
            }
        }
    }

}