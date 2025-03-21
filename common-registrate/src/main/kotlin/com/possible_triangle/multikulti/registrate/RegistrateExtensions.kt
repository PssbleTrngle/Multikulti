@file:JvmName("RegistrateExtensions")
@file:JvmMultifileClass

package com.possible_triangle.multikulti.registrate

import com.possible_triangle.multikulti.registrate.mixin.RegistrateAccessor
import com.tterrag.registrate.AbstractRegistrate


private val AbstractRegistrate<*>.accessor
    get() = this as RegistrateAccessor

fun AbstractRegistrate<*>.getCurrentName(): String = accessor.invokeCurrentName()