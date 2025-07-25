package com.possible_triangle.multikulti.datagen.conditions

import com.possible_triangle.multikulti.platform.LOGGER
import com.possible_triangle.multikulti.platform.dev

private fun Any.warnNotConditional() {
    LOGGER.dev("trying to add a condition to non-conditional {}", javaClass.simpleName)
}

private val STUB = ConditionHolder()

interface Conditional {

    fun `multikulti$conditions`(): ConditionHolder

    companion object {
        @JvmStatic
        fun <T : Any> of(value: T): ConditionHolder {
            if (value is Conditional) return value.`multikulti$conditions`()
            value.warnNotConditional()
            return STUB
        }

        @JvmStatic
        fun <T : Any> with(value: T, vararg conditions: Condition): T = with(value, conditions.toList())

        @JvmStatic
        fun <T : Any> with(value: T, conditions: Collection<Condition>): T = value.apply {
            of(value).add(conditions)
        }
    }

}

fun <T : Any> T.`when`(vararg conditions: Condition): T =
    Conditional.with(this, *conditions)