package com.possible_triangle.multikulti.registrate.builder

import com.possible_triangle.multikulti.registrate.platform.service.FabricRegistrateBuilders
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.builders.BuilderCallback
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.decoration.PaintingVariant

class FabricPaintingBuilder<TParent : Any>(
    owner: AbstractRegistrate<*>,
    parent: TParent,
    name: String,
    callback: BuilderCallback,
) : PaintingBuilder<TParent>(owner, parent, name, callback) {
    override fun tag(tag: TagKey<PaintingVariant>): PaintingBuilder<TParent> = tag(FabricRegistrateBuilders.PAINTING_TAGS, tag)
}
