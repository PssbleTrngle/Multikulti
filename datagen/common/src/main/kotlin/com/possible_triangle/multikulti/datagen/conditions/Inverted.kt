package com.possible_triangle.multikulti.datagen.conditions

import com.google.gson.JsonObject

data class Inverted(
    val condition: Condition,
) : Condition {
    override fun JsonObject.toForge() {
        if (condition is Inverted) {
            return with(condition.condition) {
                toForge()
            }
        }

        addProperty("type", "neoforge:not")
        add("value", condition.encodeToForge())
    }

    override fun JsonObject.toFabric() {
        if (condition is Inverted) {
            return with(condition.condition) {
                toFabric()
            }
        }

        addProperty("condition", "fabric:not")
        add("value", condition.encodeToFabric())
    }
}
