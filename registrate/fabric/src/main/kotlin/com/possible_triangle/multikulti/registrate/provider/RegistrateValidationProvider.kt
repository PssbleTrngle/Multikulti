package com.possible_triangle.multikulti.registrate.provider

import com.possible_triangle.multikulti.registrate.platform.ValidationContext
import com.possible_triangle.multikulti.registrate.platform.service.FabricRegistrateBuilders
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.providers.RegistrateProvider
import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper
import net.fabricmc.api.EnvType
import net.minecraft.data.CachedOutput
import net.minecraft.data.DataProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.PackType
import java.util.concurrent.CompletableFuture

class RegistrateValidationProvider(
    private val owner: AbstractRegistrate<*>,
    val fileHelper: ExistingFileHelper
) : DataProvider, RegistrateProvider, ValidationContext {

    override fun run(output: CachedOutput): CompletableFuture<*> {
        owner.genData(FabricRegistrateBuilders.VALIDATION, this)

        return CompletableFuture.completedFuture(null)
    }

    override fun getName() = "Validation"

    override fun getSide() = EnvType.SERVER

    override fun exists(
        loc: ResourceLocation,
        packType: PackType,
        pathSuffix: String,
        pathPrefix: String
    ) = fileHelper.exists(loc, packType, pathSuffix, pathPrefix)

}