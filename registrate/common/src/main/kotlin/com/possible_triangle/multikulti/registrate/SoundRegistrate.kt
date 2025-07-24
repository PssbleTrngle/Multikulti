@file:JvmName("RegistrateExtensions")
@file:JvmMultifileClass

package com.possible_triangle.multikulti.registrate

import com.possible_triangle.multikulti.registrate.builder.SoundBuilder
import com.possible_triangle.multikulti.registrate.platform.service.RegistrateBuilders
import com.tterrag.registrate.AbstractRegistrate

fun <T : AbstractRegistrate<T>> T.sound(name: String = getCurrentName()): SoundBuilder<T> {
    return entry(name) { callback ->
        RegistrateBuilders.INSTANCE.sound(this, this, name, callback)
    }
}