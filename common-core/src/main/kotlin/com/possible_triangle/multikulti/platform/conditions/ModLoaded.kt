package com.possible_triangle.multikulti.platform.conditions

import com.google.gson.JsonArray
import com.google.gson.JsonObject

data class ModLoaded(val id: String) : Condition {

    override fun JsonObject.toForge() {
        addProperty("type", "forge:mod_loaded")
        addProperty("modid", id)
    }

    override fun JsonObject.toFabric() {
        addProperty("type", "fabric:all_mods_loaded")
        add("values", JsonArray().apply {
            add(id)
        })
    }
}
