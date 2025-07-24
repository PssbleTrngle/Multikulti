package com.possible_triangle.multikulti.platform.conditions

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject

private fun JsonObject.appendAt(key: String, values: Collection<JsonElement>) {
    if(values.isEmpty()) return

    val array = if (has(key))
        getAsJsonArray(key)
    else JsonArray().also {
        add(key, it)
    }

    values.forEach { array.add(it) }
}

class ConditionHolder {

    private val values = hashSetOf<Condition>()

    fun add(conditions: Collection<Condition>) {
        values.addAll(conditions)
    }

    fun get(): Collection<Condition> {
        return values
    }

    fun encode(json: JsonObject) = json.apply {
        json.appendAt("fabric:load_conditions", values.map { condition ->
            condition.toFabric()
        })

        json.appendAt("conditions", values.map { condition ->
            condition.toForge()
        })
    }

}