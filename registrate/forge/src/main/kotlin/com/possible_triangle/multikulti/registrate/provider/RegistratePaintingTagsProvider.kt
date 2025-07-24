package com.possible_triangle.multikulti.registrate.provider

import com.possible_triangle.multikulti.registrate.platform.service.ForgeRegistrateBuilders
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.providers.RegistrateTagsProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraft.world.entity.decoration.PaintingVariant
import net.minecraftforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class RegistratePaintingTagsProvider(
    owner: AbstractRegistrate<*>,
    output: PackOutput,
    lookup: CompletableFuture<HolderLookup.Provider>,
    existingFiles: ExistingFileHelper,
) : RegistrateTagsProvider.IntrinsicImpl<PaintingVariant>(
    owner,
    ForgeRegistrateBuilders.PAINTING_TAGS,
    "Painting Tags",
    output,
    Registries.PAINTING_VARIANT,
    lookup,
    { error("Not implemented") },
    existingFiles
) {


}