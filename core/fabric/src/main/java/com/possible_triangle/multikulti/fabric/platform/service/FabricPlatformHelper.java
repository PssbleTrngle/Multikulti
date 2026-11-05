package com.possible_triangle.multikulti.fabric.platform.service;

import com.possible_triangle.multikulti.Loader;
import com.possible_triangle.multikulti.platform.service.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public Loader getLoader() {
        return Loader.FABRIC;
    }

    @Override
    public boolean isDev() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

}
