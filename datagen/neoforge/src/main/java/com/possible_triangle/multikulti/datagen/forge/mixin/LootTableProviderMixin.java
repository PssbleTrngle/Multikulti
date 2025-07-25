package com.possible_triangle.multikulti.datagen.forge.mixin;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import net.minecraft.data.loot.LootTableProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = LootTableProvider.class, remap = false)
public class LootTableProviderMixin {

    @WrapOperation(
            method = "lambda$run$3(Lnet/minecraft/data/CachedOutput;Ljava/util/Map$Entry;)Ljava/util/concurrent/CompletableFuture;",
            at = @At(value = "INVOKE", target = "Lcom/google/gson/Gson;toJsonTree(Ljava/lang/Object;)Lcom/google/gson/JsonElement;")
    )
    private JsonElement serializeConditions(Gson instance, Object value, Operation<JsonElement> original) {
        var json = original.call(instance, value).getAsJsonObject();
        return Conditional.of(value).encode(json);
    }

}
