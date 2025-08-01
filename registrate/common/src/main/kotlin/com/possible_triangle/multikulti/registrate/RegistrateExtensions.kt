@file:JvmName("RegistrateExtensions")
@file:JvmMultifileClass

package com.possible_triangle.multikulti.registrate

import com.possible_triangle.multikulti.registrate.mixin.RegistrateAccessor
import com.possible_triangle.multikulti.registrate.platform.ValidationContext
import com.possible_triangle.multikulti.registrate.platform.service.RegistrateBuilders
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.builders.Builder


private val AbstractRegistrate<*>.accessor
    get() = this as RegistrateAccessor

fun AbstractRegistrate<*>.getCurrentName(): String = accessor.invokeCurrentName()

fun <T : Builder<*,*,*,*>> T.validate(block: ValidationContext.() -> Unit): T = apply {
    RegistrateBuilders.INSTANCE.validate(this, block)
}