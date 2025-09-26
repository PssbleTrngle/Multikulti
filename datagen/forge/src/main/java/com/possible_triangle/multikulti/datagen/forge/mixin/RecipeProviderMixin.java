package com.possible_triangle.multikulti.datagen.forge.mixin;

import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = RecipeProvider.class, remap = false)
public class RecipeProviderMixin {

    @WrapOperation(
            method = "lambda$run$15(Ljava/util/Set;Ljava/util/List;Lnet/minecraft/data/CachedOutput;Lnet/minecraft/data/recipes/FinishedRecipe;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/data/recipes/FinishedRecipe;serializeRecipe()Lcom/google/gson/JsonObject;")
    )
    private JsonObject serializeConditions(FinishedRecipe instance, Operation<JsonObject> original) {
        var json = original.call(instance);
        return Conditional.merge(this, instance).encode(json);
    }

    @WrapOperation(
            method = "lambda$run$15(Ljava/util/Set;Ljava/util/List;Lnet/minecraft/data/CachedOutput;Lnet/minecraft/data/recipes/FinishedRecipe;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/data/recipes/FinishedRecipe;serializeAdvancement()Lcom/google/gson/JsonObject;")
    )
    private JsonObject serializeAdvancementConditions(FinishedRecipe instance, Operation<JsonObject> original) {
        var json = original.call(instance);
        return Conditional.merge(this, instance).encode(json);
    }

}
