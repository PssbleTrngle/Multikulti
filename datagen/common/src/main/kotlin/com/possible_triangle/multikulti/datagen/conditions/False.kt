package com.possible_triangle.multikulti.datagen.conditions

import com.google.gson.JsonObject

object False : Condition {
    private val inverted get() = Inverted(True)

    override fun JsonObject.toForge() {
        addProperty("type", "neoforge:false")
    }

    override fun JsonObject.toFabric() {
        with(inverted) {
            toFabric()
        }
    }
}
