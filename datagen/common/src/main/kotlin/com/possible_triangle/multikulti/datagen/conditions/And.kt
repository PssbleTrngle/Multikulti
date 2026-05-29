package com.possible_triangle.multikulti.datagen.conditions

data class And(
    override val conditions: Collection<Condition>,
) : CollectionCondition {
    override val key = "and"

    constructor(vararg conditions: Condition) : this(conditions.asList())
}
