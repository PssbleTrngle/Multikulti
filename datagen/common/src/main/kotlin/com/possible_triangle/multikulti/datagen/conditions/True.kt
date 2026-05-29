package com.possible_triangle.multikulti.datagen.conditions

import com.google.gson.JsonObject

object True : Condition {
    override fun JsonObject.toForge() {
        addProperty("type", "neoforge:true")
    }

    override fun JsonObject.toFabric() {
        addProperty("condition", "fabric:true")
    }
}
