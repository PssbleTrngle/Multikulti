package com.possible_triangle.multikulti.registrate.builder

import com.possible_triangle.multikulti.registrate.platform.service.RegistrateProviders
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.builders.AbstractBuilder
import com.tterrag.registrate.builders.BuilderCallback
import io.github.fabricators_of_create.porting_lib.data.SoundDefinition
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent

class SoundBuilder<TParent : Any>(
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

    init {
        setData(RegistrateProviders.INSTANCE.sounds) { context, provider ->
            val definition = SoundDefinition.definition()
            subtitleKey?.let(definition::subtitle)
            sounds.forEach {
                definition.with(SoundDefinition.Sound.sound(it, SoundDefinition.SoundType.SOUND))
            }

            provider.register(context.get(), definition)
        }
    }

    private var subtitleKey: String? = null
    private val sounds = hashSetOf<ResourceLocation>()

    fun lang(key: String, translation: String): SoundBuilder<TParent> = lang({ key }, translation)

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