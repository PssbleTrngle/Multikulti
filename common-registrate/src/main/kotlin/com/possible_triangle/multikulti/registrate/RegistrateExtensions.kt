@file:JvmName("RegistrateExtensions")

package com.possible_triangle.multikulti.registrate

import com.tterrag.registrate.AbstractRegistrate


private val AbstractRegistrate<*>.accessor
    get() = this as RegistrateAccessor

fun AbstractRegistrate<*>.getCurrentName() = accessor.getCurrentName()