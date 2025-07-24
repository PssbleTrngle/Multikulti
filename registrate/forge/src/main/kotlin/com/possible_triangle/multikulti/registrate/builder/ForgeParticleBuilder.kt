package com.possible_triangle.multikulti.registrate.builder

import com.possible_triangle.multikulti.registrate.platform.service.ForgeRegistrateBuilders
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.builders.BuilderCallback
import com.tterrag.registrate.util.OneTimeEventReceiver
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.particle.SpriteSet
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.client.event.RegisterParticleProvidersEvent
import net.minecraftforge.fml.DistExecutor

class ForgeParticleBuilder<TOptions : ParticleOptions, TType : ParticleType<TOptions>, TParent : Any>(
    owner: AbstractRegistrate<*>,
    parent: TParent,
    name: String,
    callback: BuilderCallback,
    factory: () -> TType,
    private val provider: (sprites: SpriteSet) -> ParticleProvider<TOptions>
) : ParticleBuilder<TOptions, TType, TParent>(owner, parent, name, callback, factory) {

    init {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT) { Runnable(this::registerProvider) }

        setData(ForgeRegistrateBuilders.PARTICLES) { context, provider ->
            provider.spriteSet(context.get(), sprites)
        }
    }

    private fun registerProvider() {
        OneTimeEventReceiver.addModListener(owner, RegisterParticleProvidersEvent::class.java) {
            it.registerSpriteSet(entry, provider)
        }
    }

}