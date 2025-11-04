package com.possible_triangle.multikulti.datagen.conditions

import com.google.gson.JsonObject
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

data class TagEmpty(val tag: ResourceLocation) : Condition {

    constructor(tag: TagKey<Item>) : this(tag.location())

    private val inverted get() = Inverted(TagPopulated(tag))

    override fun JsonObject.toForge() {
        addProperty("type", "neoforge:tag_empty")
        addProperty("tag", tag.toString())
    }

    override fun JsonObject.toFabric() = with(inverted) {
        toFabric()
    }

}
