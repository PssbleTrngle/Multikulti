@file:JvmName("RegistrateExtensions")
@file:JvmMultifileClass

package com.possible_triangle.multikulti.registrate

import com.possible_triangle.multikulti.registrate.builder.PaintingBuilder
import com.tterrag.registrate.AbstractRegistrate

fun <T : AbstractRegistrate<T>> T.painting(name: String = getCurrentName()): PaintingBuilder<T> {
    return entry(name) { callback ->
        PaintingBuilder(this, this, name, callback)
    }
}