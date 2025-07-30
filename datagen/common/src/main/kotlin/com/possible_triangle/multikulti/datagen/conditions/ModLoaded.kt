package com.possible_triangle.multikulti.datagen.conditions

import com.google.gson.JsonArray
import com.google.gson.JsonObject

data class ModLoaded @JvmOverloads constructor(val ids: Collection<String>, val any: Boolean = false) : Condition {

    @JvmOverloads
    constructor(vararg ids: String, any: Boolean = false) : this(ids.toList(), any)

    private data class ForgeModLoaded(val id: String) : Condition {
        override fun JsonObject.toForge() {
            addProperty("type", "neoforge:mod_loaded")
            addProperty("modid", id)
        }

        override fun JsonObject.toFabric() = error("forge only")
    }

    override fun JsonObject.toForge() {
        val conditions = ids.map { ForgeModLoaded(it) }
        val collection = if (any) Or(conditions) else And(conditions)
        with(collection) {
            toForge()
        }
    }

    override fun JsonObject.toFabric() {
        val type = if (any) "any" else "all"
        addProperty("condition", "fabric:${type}_mods_loaded")
        add("values", JsonArray().apply {
            ids.forEach {
                add(it)
            }
        })
    }

}
