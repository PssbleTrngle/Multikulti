package com.possible_triangle.multikulti.datagen.forge.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.serialization.Codec;
import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import com.possible_triangle.multikulti.datagen.forge.NeoforgeConditionExtender;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.common.conditions.ConditionalOps;
import net.neoforged.neoforge.common.conditions.WithConditions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = LootTableProvider.class, remap = false)
public class LootTableProviderMixin {

    @Unique
    private final Codec<Optional<WithConditions<LootTable>>> CONDITIONAL_CODEC = ConditionalOps.createConditionalCodecWithConditions(LootTable.DIRECT_CODEC);

    @WrapOperation(
            method = "lambda$run$4(Lnet/minecraft/data/CachedOutput;Lnet/minecraft/core/HolderLookup$Provider;Ljava/util/Map$Entry;)Ljava/util/concurrent/CompletableFuture;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/data/DataProvider;saveStable(Lnet/minecraft/data/CachedOutput;Lnet/minecraft/core/HolderLookup$Provider;Lcom/mojang/serialization/Codec;Ljava/lang/Object;Ljava/nio/file/Path;)Ljava/util/concurrent/CompletableFuture;")
    )
    private CompletableFuture<?> serializeConditions(CachedOutput cachedOutput, HolderLookup.Provider provider, Codec<LootTable> codec, Object object, Path path, Operation<CompletableFuture<?>> original) {
        var conditions = NeoforgeConditionExtender.extend(Conditional.of(object));
        return original.call(cachedOutput, provider, CONDITIONAL_CODEC, Optional.of(new WithConditions<>(object, conditions)), path);
    }

}
