package com.possible_triangle.multikulti.datagen.conditions

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.possible_triangle.multikulti.datagen.MultikultiDatagenConfig
import com.possible_triangle.multikulti.datagen.MultikultiDatagenConfig.Loader
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

interface IReadOnlyConditionHolder {
    fun get(): Collection<Condition>

    fun encode(json: JsonObject?) = json?.apply {
        if (MultikultiDatagenConfig.INSTANCE.targets.contains(Loader.FABRIC)) {
            appendAt("fabric:load_conditions", get().map { condition ->
                condition.encodeToFabric()
            })
        }

        if (MultikultiDatagenConfig.INSTANCE.targets.contains(Loader.FORGE)) {
            appendAt("neoforge:conditions", get().map { condition ->
                condition.encodeToForge()
            })
        }
    }
}

interface IConditionHolder : IReadOnlyConditionHolder {
    fun with(conditions: Collection<Condition>, block: () -> Unit)
    fun add(conditions: Collection<Condition>)
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
    private val temporary: ArrayDeque<Condition> = ArrayDeque()

    override fun with(conditions: Collection<Condition>, block: () -> Unit) {
        conditions.forEach(temporary::addLast)
        block()
        repeat(conditions.size) { temporary.removeLast() }
    }

    override fun add(conditions: Collection<Condition>) {
        values.addAll(conditions)
    }

    override fun get(): Collection<Condition> {
        return values + temporary
    }

}

class ReadonlyConditionHolder(private val conditions: Collection<Condition>) : IReadOnlyConditionHolder {
    override fun get() = conditions
}

fun IReadOnlyConditionHolder.merge(other: IReadOnlyConditionHolder): IReadOnlyConditionHolder =
    ReadonlyConditionHolder(get() + other.get())