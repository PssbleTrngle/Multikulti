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

    protected val sounds = hashSetOf<ResourceLocation>()

    abstract fun lang(key: String, translation: String): SoundBuilder<TParent>

    fun lang(translation: String): SoundBuilder<TParent> {
        if (subtitleKey == null) subtitleKey = "subtitle.$name"
        return lang(subtitleKey!!, translation)
    }

    fun with(vararg sounds: ResourceLocation): SoundBuilder<TParent> = apply {
        this.sounds.addAll(sounds)
    }

    fun with(vararg sounds: String): SoundBuilder<TParent> {
        val ids = sounds.map { ResourceLocation(owner.modid, it) }
        return with(*ids.toTypedArray())
    }

    override fun createEntry(): SoundEvent {
        check(sounds.isNotEmpty()) {
            "cannot create SoundEvent without any sounds"
        }

        val id = ResourceLocation(owner.modid, name)
        return SoundEvent.createFixedRangeEvent(id, 1F)
    }

}