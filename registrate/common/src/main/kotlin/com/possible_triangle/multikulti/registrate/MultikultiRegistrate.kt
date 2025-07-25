package com.possible_triangle.multikulti.registrate

import com.tterrag.registrate.AbstractRegistrate
import com.possible_triangle.multikulti.registrate.painting as createPainting
import com.possible_triangle.multikulti.registrate.sound as createSound

open class MultikultiRegistrate<T : MultikultiRegistrate<T>>(modId: String) :
    AbstractRegistrate<T>(modId) {

    @JvmOverloads
    fun sound(name: String = currentName()) = createSound(name)

    @JvmOverloads
    fun painting(name: String = currentName()) = createPainting(name)

}
