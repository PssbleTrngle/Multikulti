package com.possible_triangle.multikulti.registrate.provider

import com.possible_triangle.multikulti.registrate.platform.service.FabricRegistrateBuilders
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.providers.ProviderType
import com.tterrag.registrate.providers.RegistrateTagsProvider
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.core.HolderLookup
import net.minecraft.core.Registry
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraft.world.entity.decoration.PaintingVariant
import java.util.concurrent.CompletableFuture

class RegistratePaintingTagsProvider(
    owner: AbstractRegistrate<*>,
    output: FabricDataOutput,
    lookup: CompletableFuture<HolderLookup.Provider>,
) : RegistrateTagsProvider.IntrinsicImpl<PaintingVariant>(
    owner,
    FabricRegistrateBuilders.PAINTING_TAGS,
    "Painting Tags",
    output,
    Registries.PAINTING_VARIANT,
    lookup,
    { error("Not implemented") }
) {

    init {
        ProviderType.ITEM_TAGS
    }

}