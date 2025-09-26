package com.possible_triangle.multikulti.datagen.fabric.mixin;

import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.sugar.Local;
import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import java.util.List;
import java.util.Set;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = FabricRecipeProvider.class, remap = false)
public class FabricRecipeProviderMixin {

    @Inject(
            method = "lambda$run$1(Ljava/util/Set;Ljava/util/List;Lnet/minecraft/data/CachedOutput;Lnet/minecraft/data/recipes/FinishedRecipe;)V",
            at = @At(value = "INVOKE", ordinal = 0, shift = At.Shift.AFTER, target = "Lnet/fabricmc/fabric/api/resource/conditions/v1/ConditionJsonProvider;write(Lcom/google/gson/JsonObject;[Lnet/fabricmc/fabric/api/resource/conditions/v1/ConditionJsonProvider;)V")
    )
    private void serializeConditions(Set<?> generatedRecipes, List<?> list, CachedOutput writer, FinishedRecipe provider, CallbackInfo ci, @Local(ordinal = 0) JsonObject json, @Local FinishedRecipe recipe) {
        Conditional.merge(this, recipe).encode(json);
    }

    @Inject(
            method = "lambda$run$1(Ljava/util/Set;Ljava/util/List;Lnet/minecraft/data/CachedOutput;Lnet/minecraft/data/recipes/FinishedRecipe;)V",
            at = @At(value = "INVOKE", ordinal = 1, shift = At.Shift.AFTER, target = "Lnet/fabricmc/fabric/api/resource/conditions/v1/ConditionJsonProvider;write(Lcom/google/gson/JsonObject;[Lnet/fabricmc/fabric/api/resource/conditions/v1/ConditionJsonProvider;)V")
    )
    private void serializeAdvancementConditions(Set<?> generatedRecipes, List<?> list, CachedOutput writer, FinishedRecipe provider, CallbackInfo ci, @Local(ordinal = 1) JsonObject json, @Local FinishedRecipe recipe) {
        Conditional.merge(this, recipe).encode(json);
    }

}
