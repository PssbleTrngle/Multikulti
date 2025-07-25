package com.possible_triangle.multikulti.fabric.platform.service;

import com.possible_triangle.multikulti.platform.service.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public boolean isDev() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public boolean isModLoaded(@NotNull String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

}
