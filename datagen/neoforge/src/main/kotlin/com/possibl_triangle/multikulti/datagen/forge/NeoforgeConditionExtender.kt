package com.possibl_triangle.multikulti.datagen.forge

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.mojang.serialization.JsonOps
import com.possible_triangle.multikulti.datagen.conditions.IReadOnlyConditionHolder
import net.minecraft.util.GsonHelper
import net.neoforged.neoforge.common.conditions.ICondition

object NeoforgeConditionExtender {
    @JvmStatic
    fun extend(
        holder: IReadOnlyConditionHolder,
        vararg conditions: ICondition,
    ): Array<ICondition> {
        val json = JsonObject().also { holder.encode(it) }
        val encoded = GsonHelper.getAsJsonArray(json, "neoforge:conditions", JsonArray())
        val decoded =
            ICondition.LIST_CODEC
                .parse(JsonOps.INSTANCE, encoded)
                .result()
                .orElseGet(::emptyList)

        return (decoded + conditions).toTypedArray()
    }
}
