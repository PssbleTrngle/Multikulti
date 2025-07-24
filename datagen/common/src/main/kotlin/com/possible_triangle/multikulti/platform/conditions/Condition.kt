package com.possible_triangle.multikulti.platform.conditions

import com.google.gson.JsonElement
import com.google.gson.JsonObject

interface Condition {

    fun JsonObject.toForge()

    fun JsonObject.toFabric()

}
