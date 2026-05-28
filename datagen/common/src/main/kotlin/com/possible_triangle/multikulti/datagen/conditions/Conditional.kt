package com.possible_triangle.multikulti.datagen.conditions

import com.possible_triangle.multikulti.platform.LOGGER
import com.possible_triangle.multikulti.platform.dev

private fun Any.warnNotConditional() {
    LOGGER.dev("trying to access condition of a non-conditional {}", javaClass.name)
}

private val STUB = ConditionalHolderStub()

interface Conditional {
    @Suppress("ktlint:standard:function-naming")
    fun `multikulti$conditions`(): IConditionHolder

    companion object {
        @JvmStatic
        fun <T : Any> of(value: T): IConditionHolder {
            if (value is Conditional) return value.`multikulti$conditions`()
            value.warnNotConditional()
            return STUB
        }

        @JvmStatic
        fun <T : Any> with(
            value: T,
            vararg conditions: Condition,
        ): T = with(value, conditions.toList())

        @JvmStatic
        fun <T : Any> with(
            value: T,
            conditions: Collection<Condition>,
        ): T =
            value.apply {
                if (conditions.isNotEmpty()) of(value).add(conditions)
            }

        @JvmStatic
        fun <T : Any> with(
            value: T,
            vararg conditions: Condition,
            block: Runnable,
        ): T = with(value, conditions.toList(), block)

        @JvmStatic
        fun <T : Any> with(
            value: T,
            conditions: Collection<Condition>,
            block: Runnable,
        ): T =
            value.apply {
                of(this).with(conditions.toList(), block::run)
            }

        @JvmStatic
        fun merge(vararg values: Any): IReadOnlyConditionHolder {
            val holders = values.map { of(it) as IReadOnlyConditionHolder }.toList()
            return holders.reduce { a, b -> a.merge(b) }
        }
    }
}

fun <T : Any> T.`when`(vararg conditions: Condition): T = Conditional.with(this, *conditions)

fun <T : Any> T.withConditions(
    vararg conditions: Condition,
    block: () -> Unit,
): T = Conditional.with(this, conditions.toList(), block)
