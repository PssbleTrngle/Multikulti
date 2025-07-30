package com.possible_triangle.multikulti.datagen.fabric.mixin;

import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.sugar.Local;
import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider$2", remap = false)
public class FabricRecipeProviderMixin {

    @Inject(
            method = "accept(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/world/item/crafting/Recipe;Lnet/minecraft/advancements/AdvancementHolder;)V",
            at = @At(value = "INVOKE", ordinal = 0, shift = At.Shift.AFTER, target = "Lnet/fabricmc/fabric/impl/datagen/FabricDataGenHelper;addConditions(Lcom/google/gson/JsonObject;[Lnet/fabricmc/fabric/api/resource/conditions/v1/ResourceCondition;)V")
    )
    private void serializeConditions(ResourceLocation id, Recipe<?> recipe, AdvancementHolder advancement, CallbackInfo ci, @Local(ordinal = 0) JsonObject json) {
        Conditional.of(recipe).encode(json);
    }

    @Inject(
            method = "accept(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/world/item/crafting/Recipe;Lnet/minecraft/advancements/AdvancementHolder;)V",
            at = @At(value = "INVOKE", ordinal = 1, shift = At.Shift.AFTER, target = "Lnet/fabricmc/fabric/impl/datagen/FabricDataGenHelper;addConditions(Lcom/google/gson/JsonObject;[Lnet/fabricmc/fabric/api/resource/conditions/v1/ResourceCondition;)V")
    )
    private void serializeAdvancementConditions(ResourceLocation recipeId, Recipe<?> recipe, AdvancementHolder advancement, CallbackInfo ci, @Local(ordinal = 1) JsonObject json) {
        Conditional.of(recipe).encode(json);
    }

}
