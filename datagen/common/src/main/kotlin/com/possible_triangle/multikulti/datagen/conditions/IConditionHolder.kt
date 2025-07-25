package com.possible_triangle.multikulti.datagen.conditions

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import kotlin.collections.Collection
import kotlin.collections.forEach
import kotlin.collections.hashSetOf
import kotlin.collections.map


private fun JsonObject.appendAt(key: String, values: Collection<JsonElement>) {
    if (values.isEmpty()) return

    val array = if (has(key))
        getAsJsonArray(key)
    else JsonArray().also {
        add(key, it)
    }

    values.forEach { array.add(it) }
}

interface IConditionHolder {
    fun with(conditions: Collection<Condition>, block: () -> Unit)
    fun add(conditions: Collection<Condition>)
    fun get(): Collection<Condition>

    fun encode(json: JsonObject) = json.apply {
        json.appendAt("fabric:load_conditions", get().map { condition ->
            condition.toFabric()
        })

        json.appendAt("conditions", get().map { condition ->
            condition.toForge()
        })
    }
}

internal class ConditionalHolderStub : IConditionHolder {
    override fun with(
        conditions: Collection<Condition>,
        block: () -> Unit
    ) {
    }

    override fun add(conditions: Collection<Condition>) {
    }

    override fun get() = emptyList<Condition>()
}

class ConditionHolder : IConditionHolder {

    private val values = hashSetOf<Condition>()
    private var temporary: Collection<Condition> = emptyList()

    override fun with(conditions: Collection<Condition>, block: () -> Unit) {
        temporary = conditions
        block()
        temporary = emptyList()
    }

    override fun add(conditions: Collection<Condition>) {
        values.addAll(conditions)
    }

    override fun get(): Collection<Condition> {
        return values + temporary
    }

}