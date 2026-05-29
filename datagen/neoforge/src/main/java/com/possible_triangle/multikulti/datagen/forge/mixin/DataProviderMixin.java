package com.possible_triangle.multikulti.datagen.forge.mixin;

import com.google.gson.JsonElement;
import com.llamalad7.mixinextras.sugar.Local;
import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import java.util.Optional;
import net.minecraft.data.DataProvider;
import net.neoforged.neoforge.common.conditions.WithConditions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(DataProvider.class)
public interface DataProviderMixin {

    @ModifyArg(
        method = "saveStable(Lnet/minecraft/data/CachedOutput;Lnet/minecraft/core/HolderLookup$Provider;Lcom/mojang/serialization/Codec;Ljava/lang/Object;Ljava/nio/file/Path;)Ljava/util/concurrent/CompletableFuture;",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/data/DataProvider;saveStable(Lnet/minecraft/data/CachedOutput;Lcom/google/gson/JsonElement;Ljava/nio/file/Path;)Ljava/util/concurrent/CompletableFuture;")
    )
    private static <T> JsonElement encodeFabricConditions(JsonElement json, @Local T value) {
        if (value instanceof Optional<?> optional && optional.isPresent()
            && optional.get() instanceof WithConditions<?> withConditions) {
            var carrier = withConditions.carrier();
            Conditional.of(carrier).encodeFabric(json.getAsJsonObject());
        }

        return json;
    }

}
