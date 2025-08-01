package com.possible_triangle.multikulti.registrate.mixin;

import com.possible_triangle.multikulti.registrate.platform.service.RegistrateBuilders;
import com.tterrag.registrate.providers.DataProviderInitializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DataProviderInitializer.class)
public class DataProviderInitializerMixin {

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void registerDependencies(CallbackInfo ci) {
        var self = (DataProviderInitializer) (Object) (this);
        RegistrateBuilders.INSTANCE.registerDependencies(self);
    }

}
