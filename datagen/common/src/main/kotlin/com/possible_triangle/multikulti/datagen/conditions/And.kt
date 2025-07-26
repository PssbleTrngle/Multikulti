package com.possible_triangle.multikulti.datagen.conditions

data class And(override val conditions: Collection<Condition>) : CollectionCondition {

    override val key = "and"
}