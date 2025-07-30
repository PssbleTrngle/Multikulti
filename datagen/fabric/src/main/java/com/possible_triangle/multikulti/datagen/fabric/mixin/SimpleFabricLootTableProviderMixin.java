package com.possible_triangle.multikulti.datagen.fabric.mixin;

import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.sugar.Local;
import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLootTableProvider;
import net.fabricmc.fabric.impl.datagen.loot.FabricLootTableProviderImpl;
import net.minecraft.core.HolderLookup;
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
            method = "lambda$run$2(Lnet/fabricmc/fabric/api/datagen/v1/provider/FabricLootTableProvider;Ljava/util/HashMap;Ljava/util/HashMap;Lnet/minecraft/world/level/storage/loot/parameters/LootContextParamSet;Lnet/minecraft/data/CachedOutput;Lnet/fabricmc/fabric/api/datagen/v1/FabricDataOutput;Lnet/minecraft/core/HolderLookup$Provider;)Ljava/util/concurrent/CompletionStage;",
            at = @At(value = "INVOKE", target = "Lnet/fabricmc/fabric/impl/datagen/FabricDataGenHelper;addConditions(Lcom/google/gson/JsonObject;[Lnet/fabricmc/fabric/api/resource/conditions/v1/ResourceCondition;)V"),
            remap = false
    )
    private static void serializeConditions(FabricLootTableProvider provider, HashMap<?,?> conditionMap, HashMap<?,?> builders, LootContextParamSet paramSet, CachedOutput writer, FabricDataOutput output, HolderLookup.Provider lookup, CallbackInfoReturnable<CompletionStage<?>> cir, @Local(ordinal = 0) JsonObject json, @Local Map.Entry<ResourceLocation, LootTable> entry) {
        Conditional.of(entry.getValue()).encode(json);
    }

}
