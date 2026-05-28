package com.possible_triangle.multikulti.datagen.conditions

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

data class TagPopulated(
    val tag: ResourceLocation,
) : Condition {
    constructor(tag: TagKey<Item>) : this(tag.location())

    private val inverted get() = Inverted(TagEmpty(tag))

    override fun JsonObject.toForge() =
        with(inverted) {
            toForge()
        }

    override fun JsonObject.toFabric() {
        addProperty("condition", "fabric:tags_populated")
        add(
            "values",
            JsonArray().apply {
                add(tag.toString())
            },
        )
    }
}
