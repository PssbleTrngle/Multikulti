package com.possible_triangle.multikulti.registrate.builder

import com.possible_triangle.multikulti.registrate.platform.service.ForgeRegistrateBuilders
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.builders.BuilderCallback
import net.neoforged.neoforge.common.data.SoundDefinition

class ForgeSoundBuilder<TParent : Any>(
    owner: AbstractRegistrate<*>,
    parent: TParent,
    name: String,
    callback: BuilderCallback,
) : SoundBuilder<TParent>(owner, parent, name, callback) {
    init {
        setData(ForgeRegistrateBuilders.SOUNDS) { context, provider ->
            val definition = SoundDefinition.definition()
            subtitleKey?.let(definition::subtitle)
            sounds.forEach {
                definition.with(
                    SoundDefinition.Sound
                        .sound(it.id, SoundDefinition.SoundType.SOUND)
                        .stream(it.stream ?: stream)
                        .volume(it.volume)
                        .pitch(it.pitch)
                        .weight(it.weight),
                )
            }

            provider.add(context.get(), definition)
        }
    }

    // TODO check if this can be in super class
    override fun lang(
        key: String,
        translation: String,
    ): SoundBuilder<TParent> = lang({ key }, translation)
}
