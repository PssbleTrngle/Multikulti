package com.possible_triangle.multikulti.forge.platform.service

import com.possible_triangle.multikulti.platform.service.IPlatformHelper
import net.minecraftforge.fml.ModList
import net.minecraftforge.fml.loading.FMLLoader

class ForgePlatformHelper : IPlatformHelper {

    override fun isDev(): Boolean {
        return !FMLLoader.isProduction()
    }

    override fun isModLoaded(modId: String): Boolean {
        return ModList.get().isLoaded(modId)
    }

}