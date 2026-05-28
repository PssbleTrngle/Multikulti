package com.possible_triangle.multikulti.registrate.builder

import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.builders.AbstractBuilder
import com.tterrag.registrate.builders.BuilderCallback
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.particle.SpriteSet
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation

abstract class ParticleBuilder<TOptions : ParticleOptions, TType : ParticleType<TOptions>, TParent : Any>(
    owner: AbstractRegistrate<*>,
    parent: TParent,
    name: String,
    callback: BuilderCallback,
    private val factory: () -> TType,
) : AbstractBuilder<ParticleType<*>, TType, TParent, ParticleBuilder<TOptions, TType, TParent>>(
        owner,
        parent,
        name,
        callback,
        Registries.PARTICLE_TYPE,
    ) {
    private val _sprites = arrayListOf<ResourceLocation>()

    val sprites: List<ResourceLocation>
        get() =
            _sprites.ifEmpty {
                listOf(key.location())
            }

    fun sprite(vararg textures: ResourceLocation) =
        apply {
            _sprites.addAll(textures)
        }

    fun sprite(vararg textures: String) = sprite(*textures.map { ResourceLocation.fromNamespaceAndPath(owner.modid, it) }.toTypedArray())

    @JvmOverloads
    fun sprites(
        texture: String,
        numOfTextures: Int,
        reverse: Boolean = false,
    ) = sprites(ResourceLocation.fromNamespaceAndPath(owner.modid, texture), numOfTextures, reverse)

    @JvmOverloads
    fun sprites(
        texture: ResourceLocation = key.location(),
        numOfTextures: Int,
        reverse: Boolean = false,
    ): ParticleBuilder<TOptions, TType, TParent> {
        check(numOfTextures > 0) { "number of textures must be positive" }
        val range = if (reverse) numOfTextures.minus(1).downTo(0) else 0 until numOfTextures
        val textures = range.map { texture.withSuffix("_$it") }
        return sprite(*textures.toTypedArray())
    }

    abstract fun provider(supplier: () -> (sprites: SpriteSet) -> ParticleProvider<TOptions>): ParticleBuilder<TOptions, TType, TParent>

    override fun createEntry(): TType = factory()
}
