package com.possible_triangle.multikulti.datagen.fabric.mixin;

import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.sugar.Local;
import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLootTableProvider;
import net.fabricmc.fabric.impl.datagen.loot.FabricLootTableProviderImpl;
import net.minecraft.data.CachedOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = FabricLootTableProviderImpl.class, remap = false)
public class SimpleFabricLootTableProviderMixin {

    @Inject(
            method = "run(Lnet/minecraft/data/CachedOutput;Lnet/fabricmc/fabric/api/datagen/v1/provider/FabricLootTableProvider;Lnet/minecraft/world/level/storage/loot/parameters/LootContextParamSet;Lnet/fabricmc/fabric/api/datagen/v1/FabricDataOutput;)Ljava/util/concurrent/CompletableFuture;",
            at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/fabricmc/fabric/api/resource/conditions/v1/ConditionJsonProvider;write(Lcom/google/gson/JsonObject;[Lnet/fabricmc/fabric/api/resource/conditions/v1/ConditionJsonProvider;)V")
    )
    private static void serializeConditions(CachedOutput writer, FabricLootTableProvider provider, LootContextParamSet paramSet, FabricDataOutput output, CallbackInfoReturnable<CompletableFuture<?>> cir, @Local(ordinal = 0) JsonObject json, @Local Map.Entry<ResourceLocation, LootTable> entry) {
        Conditional.of(entry.getValue()).encode(json);
    }

}
