@file:JvmName("Conditions")

package com.possible_triangle.multikulti.datagen.conditions

import com.google.gson.JsonObject

interface Condition {
    fun JsonObject.toForge()

    fun JsonObject.toFabric()
}

fun Condition.encodeToForge(): JsonObject = JsonObject().apply { toForge() }

fun Condition.encodeToFabric(): JsonObject = JsonObject().apply { toFabric() }

fun Condition.inverted() = Inverted(this)
