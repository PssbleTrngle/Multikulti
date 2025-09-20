package com.possible_triangle.multikulti.registrate.builder

import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.builders.AbstractBuilder
import com.tterrag.registrate.builders.BuilderCallback
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent

abstract class SoundBuilder<TParent : Any>(
    owner: AbstractRegistrate<*>,
    parent: TParent,
    name: String,
    callback: BuilderCallback
) : AbstractBuilder<SoundEvent, SoundEvent, TParent, SoundBuilder<TParent>>(
    owner,
    parent,
    name,
    callback,
    Registries.SOUND_EVENT
) {

    protected var subtitleKey: String? = null
        private set

    protected val sounds = hashSetOf<Entry>()

    protected var stream = false

    abstract fun lang(key: String, translation: String): SoundBuilder<TParent>

    fun lang(translation: String): SoundBuilder<TParent> {
        if (subtitleKey == null) subtitleKey = "subtitle.$name"
        return lang(subtitleKey!!, translation)
    }

    @JvmOverloads
    fun with(
        sound: ResourceLocation,
        weight: Int,
        volume: Float = 1.0F,
        pitch: Float = 1.0F,
        stream: Boolean? = null,
    ): SoundBuilder<TParent> = apply {
        sounds.add(Entry(sound, weight, volume, pitch, stream))
    }

    fun with(vararg sounds: ResourceLocation): SoundBuilder<TParent> = apply {
        sounds.forEach { with(it, 1) }
    }

    fun with(vararg sounds: String): SoundBuilder<TParent> {
        val ids = sounds.map { ResourceLocation.fromNamespaceAndPath(owner.modid, it) }
        return with(*ids.toTypedArray())
    }

    fun stream() = apply {
        stream = true
    }

    override fun createEntry(): SoundEvent {
        check(sounds.isNotEmpty()) {
            "cannot create SoundEvent without any sounds"
        }

        return SoundEvent.createFixedRangeEvent(key.location(), 1F)
    }

    data class Entry(
        val id: ResourceLocation,
        val weight: Int,
        val volume: Float,
        val pitch: Float,
        val stream: Boolean?
    )

}