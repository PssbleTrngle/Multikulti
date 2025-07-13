package com.possible_triangle.multikulti.test

import com.possible_triangle.multikulti.registrate.MultikultiRegistrate
import com.possible_triangle.multikulti.registrate.painting
import com.tterrag.registrate.util.nullness.NonNullFunction
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Item
import net.minecraftforge.fml.common.Mod
import thedarkcolour.kotlinforforge.forge.MOD_BUS

const val MOD_ID = "test"
val REGISTRATE = MultikultiRegistrate(MOD_ID)

@Mod(MOD_ID)
object TestEntrypoint {

    init {
        REGISTRATE.registerEventListeners(MOD_BUS)

        REGISTRATE.item("example", NonNullFunction(::Item))
            .tag(ItemTags.PLANKS)
            .register()

        REGISTRATE.`object`("example_painting")
            .painting()
            .lang("Example Painting Title", "Example Painting Author")
            .sized(3, 2)
            .placeable()
            .register()
    }

}