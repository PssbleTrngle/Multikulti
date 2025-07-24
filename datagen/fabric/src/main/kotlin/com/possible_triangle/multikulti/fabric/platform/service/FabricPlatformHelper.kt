package com.possible_triangle.multikulti.fabric.platform.service

import com.possible_triangle.multikulti.platform.service.IPlatformHelper
import net.fabricmc.loader.api.FabricLoader

class FabricPlatformHelper : IPlatformHelper {

    override fun isDev(): Boolean {
        return FabricLoader.getInstance().isDevelopmentEnvironment
    }

    override fun isModLoaded(modId: String): Boolean {
        return FabricLoader.getInstance().isModLoaded(modId)
    }

}