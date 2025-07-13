package com.possible_triangle.multikulti.test

import com.tterrag.registrate.Registrate
import com.tterrag.registrate.util.nullness.NonNullFunction
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Item
import org.junit.jupiter.api.Test

object ParticleTest : DatagenTest() {

    @Test
    fun `generates particle files`() {
        val registrate = Registrate.create("test")

        registrate.item("example", NonNullFunction(::Item))
            .tag(ItemTags.PLANKS)
            .register()

        runDataGen()
    }

}