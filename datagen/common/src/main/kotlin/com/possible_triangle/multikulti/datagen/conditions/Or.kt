package com.possible_triangle.multikulti.datagen.conditions

data class Or(
    override val conditions: Collection<Condition>,
) : CollectionCondition {
    override val key = "or"
}
