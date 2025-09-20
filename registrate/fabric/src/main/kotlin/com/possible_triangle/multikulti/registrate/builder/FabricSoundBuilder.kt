package com.possible_triangle.multikulti.registrate.builder

import com.possible_triangle.multikulti.registrate.platform.service.FabricRegistrateBuilders
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.builders.BuilderCallback
import io.github.fabricators_of_create.porting_lib.data.SoundDefinition

class FabricSoundBuilder<TParent : Any>(
    owner: AbstractRegistrate<*>,
    parent: TParent,
    name: String,
    callback: BuilderCallback
) : SoundBuilder<TParent>(owner, parent, name, callback) {

    init {
        setData(FabricRegistrateBuilders.SOUNDS) { context, provider ->
            val definition = SoundDefinition.definition()
            subtitleKey?.let(definition::subtitle)
            sounds.forEach {
                definition.with(
                    SoundDefinition.Sound.sound(it.id, SoundDefinition.SoundType.SOUND)
                        .stream(it.stream ?: stream)
                        .volume(it.volume)
                        .pitch(it.pitch)
                        .weight(it.weight)
                )
            }

            provider.add(context.get(), definition)
        }
    }

    // TODO check if this can be in super class
    override fun lang(key: String, translation: String): SoundBuilder<TParent> = lang({ key }, translation)

}