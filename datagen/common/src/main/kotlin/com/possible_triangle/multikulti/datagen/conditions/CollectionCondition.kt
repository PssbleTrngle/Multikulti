package com.possible_triangle.multikulti.datagen.conditions

import com.google.gson.JsonArray
import com.google.gson.JsonObject

interface CollectionCondition : Condition {

    val conditions: Collection<Condition>

    val key: String

    private fun check() {
        check(conditions.isNotEmpty()) { "at last one condition must be present" }
    }

    override fun JsonObject.toForge() {
        check()
        if (conditions.size == 1) return with(conditions.first()) { toForge() }

        addProperty("type", "forge:$key")
        add("values", JsonArray().apply {
            conditions.forEach {
                add(it.encodeToForge())
            }
        })
    }

    override fun JsonObject.toFabric() {
        check()
        if (conditions.size == 1) return with(conditions.first()) { toFabric() }

        addProperty("condition", "fabric:$key")
        add("values", JsonArray().apply {
            conditions.forEach {
                add(it.encodeToFabric())
            }
        })
    }
}
