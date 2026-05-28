@file:JvmName("RegistrateExtensions")
@file:JvmMultifileClass

package com.possible_triangle.multikulti.registrate

import com.possible_triangle.multikulti.registrate.builder.PaintingBuilder
import com.possible_triangle.multikulti.registrate.platform.service.RegistrateBuilders
import com.tterrag.registrate.AbstractRegistrate

@JvmOverloads
fun <T : AbstractRegistrate<out T>> T.painting(name: String = getCurrentName()): PaintingBuilder<T> =
    entry(name) { callback ->
        RegistrateBuilders.INSTANCE.painting(this, this, name, callback)
    }
