package com.possible_triangle.multikulti.registrate.platform

import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.PackType

interface ValidationContext {

    fun exists(loc: ResourceLocation, packType: PackType, pathSuffix: String, pathPrefix: String): Boolean

}