package com.possible_triangle.multikulti.forge.mixin;

import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.possible_triangle.multikulti.platform.conditions.Conditional;
import net.minecraft.data.recipes.RecipeProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = RecipeProvider.class, remap = false)
public class RecipeProviderMixin {

    @ModifyExpressionValue(
            method = "lambda$run$15(Ljava/util/Set;Ljava/util/List;Lnet/minecraft/data/CachedOutput;Lnet/minecraft/data/recipes/FinishedRecipe;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/data/recipes/FinishedRecipe;serializeRecipe()Lcom/google/gson/JsonObject;")
    )
    private JsonObject serializeConditions(JsonObject json) {
        return Conditional.of(this).encode(json);
    }

}
