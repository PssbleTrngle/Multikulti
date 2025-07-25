package com.possible_triangle.multikulti.datagen.conditions

import com.google.gson.JsonObject

interface Condition {

    fun JsonObject.toForge()

    fun JsonObject.toFabric()

}

fun Condition.toForge(): JsonObject {
    return JsonObject().apply { toForge() }
}

fun Condition.toFabric(): JsonObject {
    return JsonObject().apply { toFabric() }
}