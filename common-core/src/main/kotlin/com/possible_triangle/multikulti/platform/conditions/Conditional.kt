package com.possible_triangle.multikulti.platform.conditions

fun <T : Conditional> T.`when`(condition: Condition): T = apply {
    `multikulti$addCondition`(condition)
}

interface Conditional {

    fun `multikulti$addCondition`(condition: Condition)

    fun `multikulti$conditions`(): Collection<Condition>

}