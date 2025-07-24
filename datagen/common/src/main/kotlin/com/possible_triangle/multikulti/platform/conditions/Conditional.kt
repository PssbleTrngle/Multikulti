package com.possible_triangle.multikulti.platform.conditions

import com.possible_triangle.multikulti.platform.LOGGER
import com.possible_triangle.multikulti.platform.dev
import net.minecraft.data.recipes.RecipeBuilder

fun <T : RecipeBuilder> T.`when`(condition: Condition): T = apply {
    if (this is Conditional) {
        `multikulti$addCondition`(condition)
    } else {
        LOGGER.dev("trying to add a condition to non-conditional recipe builder")
    }
}

interface Conditional {

    fun `multikulti$addCondition`(condition: Condition)

    fun `multikulti$conditions`(): Collection<Condition>

}