@file:JvmName("SoundRegistrate")

package com.possible_triangle.multikulti.registrate

import com.possible_triangle.multikulti.registrate.builder.SoundBuilder
import com.tterrag.registrate.AbstractRegistrate

fun <T : AbstractRegistrate<T>> T.sound(name: String = getCurrentName()): SoundBuilder<T> {
    return entry(name) { callback ->
        SoundBuilder(this, this, name, callback)
    }
}