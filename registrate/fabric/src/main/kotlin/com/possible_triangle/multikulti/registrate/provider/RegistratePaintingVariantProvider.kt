package com.possible_triangle.multikulti.registrate.provider

import com.google.gson.JsonObject
import com.possible_triangle.multikulti.registrate.platform.service.FabricRegistrateBuilders
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.providers.RegistrateProvider
import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper
import net.fabricmc.api.EnvType
import net.minecraft.data.CachedOutput
import net.minecraft.data.DataProvider
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.PackType
import net.minecraft.world.entity.decoration.PaintingVariant
import java.util.concurrent.CompletableFuture

class RegistratePaintingVariantProvider(
    private val owner: AbstractRegistrate<*>,
    output: PackOutput,
    private val helper: ExistingFileHelper,
) : DataProvider, RegistrateProvider {

    private val pathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "painting_variant")

    override fun getSide() = EnvType.SERVER

    override fun getName() = "Painting Variants"

    private val entries = hashMapOf<ResourceLocation, PaintingVariant>()

    override fun run(cachedOutput: CachedOutput): CompletableFuture<*> {
        owner.genData(FabricRegistrateBuilders.PAINTING_VARIANTS, this)

        return CompletableFuture.allOf(*entries.map { (id, painting) ->
            val json = JsonObject().apply {
                addProperty("asset_id", painting.assetId().toString())
                addProperty("height", painting.height())
                addProperty("width", painting.width())
            }
            DataProvider.saveStable(cachedOutput, json, pathProvider.json(id))
        }.toTypedArray())
    }

    fun add(id: ResourceLocation, entry: PaintingVariant) {
        check(helper.exists(entry.assetId(), PackType.CLIENT_RESOURCES, ".png", "textures/painting")) {
            "Texture '${entry.assetId()}' does not exist in any known resource pack"
        }

        check(entries.putIfAbsent(id, entry) == null) {
            "The painting variant '$id' is already registered"
        }
    }

}