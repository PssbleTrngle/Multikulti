package com.possible_triangle.multikulti.registrate

import com.tterrag.registrate.AbstractRegistrate
import com.possible_triangle.multikulti.registrate.painting as createPainting
import com.possible_triangle.multikulti.registrate.sound as createSound

open class MultikultiRegistrate<TRegistrate : MultikultiRegistrate<TRegistrate>>(modId: String) :
    AbstractRegistrate<TRegistrate>(modId) {

    @JvmOverloads
    fun sound(name: String = currentName()) = createSound(name)

    @JvmOverloads
    fun painting(name: String = currentName()) = createPainting(name)

}
