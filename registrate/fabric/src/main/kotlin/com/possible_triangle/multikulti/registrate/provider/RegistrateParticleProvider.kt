package com.possible_triangle.multikulti.registrate.provider

import com.google.gson.JsonArray
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
import java.util.concurrent.CompletableFuture

class RegistrateParticleProvider(
    private val owner: AbstractRegistrate<*>,
    output: PackOutput,
    private val helper: ExistingFileHelper,
) : DataProvider,
    RegistrateProvider {
    private val pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "particles")

    override fun getSide() = EnvType.CLIENT

    override fun getName() = "Particle Descriptions"

    private val entries = hashMapOf<ResourceLocation, Collection<ResourceLocation>>()

    override fun run(cachedOutput: CachedOutput): CompletableFuture<*> {
        owner.genData(FabricRegistrateBuilders.PARTICLES, this)

        return CompletableFuture.allOf(
            *entries
                .map { (id, textures) ->
                    val json =
                        JsonObject().apply {
                            val array = JsonArray()
                            textures.map { it.toString() }.forEach(array::add)
                            add("textures", array)
                        }
                    DataProvider.saveStable(cachedOutput, json, pathProvider.json(id))
                }.toTypedArray(),
        )
    }

    fun spriteSet(
        id: ResourceLocation,
        textures: Collection<ResourceLocation>,
    ) {
        textures.forEach {
            check(helper.exists(it, PackType.CLIENT_RESOURCES, ".png", "textures/particle")) {
                "Texture '$it' does not exist in any known resource pack"
            }
        }

        check(entries.putIfAbsent(id, textures) == null) {
            "The particle type '$id' already has a description associated with it"
        }
    }
}
