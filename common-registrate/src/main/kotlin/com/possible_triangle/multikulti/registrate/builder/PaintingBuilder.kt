package com.possible_triangle.multikulti.registrate.builder

import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.builders.AbstractBuilder
import com.tterrag.registrate.builders.BuilderCallback
import com.tterrag.registrate.providers.ProviderType
import net.minecraft.core.registries.Registries
import net.minecraft.world.entity.decoration.PaintingVariant

class PaintingBuilder<TParent : Any>(
    owner: AbstractRegistrate<*>,
    parent: TParent,
    name: String,
    callback: BuilderCallback
) : AbstractBuilder<PaintingVariant, PaintingVariant, TParent, PaintingBuilder<TParent>>(
    owner,
    parent,
    name,
    callback,
    Registries.PAINTING_VARIANT
) {

    private var height: Int = 16
    private var width: Int = 16

    fun pixelSized(width: Int, height: Int = width) = apply {
        check(height > 0) { "height must be positive" }
        check(width > 0) { "width must be positive" }

        this.height = height
        this.width = width
    }

    fun sized(width: Int, height: Int = width) = pixelSized(width / 16, height / 16)

    fun lang(title: String, author: String) = apply {
        setData(ProviderType.LANG) { context, provider ->
            provider.add(context.id.toLanguageKey("painting", "title"), title)
            provider.add(context.id.toLanguageKey("painting", "author"), author)
        }
    }

    override fun createEntry() = PaintingVariant(width, height)

}