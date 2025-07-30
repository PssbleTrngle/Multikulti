package com.possible_triangle.multikulti.datagen.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.possible_triangle.multikulti.datagen.conditions.ConditionHolder;
import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import com.possible_triangle.multikulti.datagen.conditions.IConditionHolder;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.data.recipes.SmithingTrimRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;

@Mixin({
        ShapedRecipeBuilder.class,
        ShapelessRecipeBuilder.class,
        SimpleCookingRecipeBuilder.class,
        SingleItemRecipeBuilder.class,
        SmithingTransformRecipeBuilder.class,
        SmithingTrimRecipeBuilder.class
})
public class RecipeBuilderMixin implements Conditional {

    @Unique
    private final IConditionHolder multikulti$conditions = new ConditionHolder();

    @Override
    public IConditionHolder multikulti$conditions() {
        return multikulti$conditions;
    }

    @Coerce
    @WrapOperation(
            method = "save(Lnet/minecraft/data/recipes/RecipeOutput;Lnet/minecraft/resources/ResourceLocation;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/data/recipes/RecipeOutput;accept(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/world/item/crafting/Recipe;Lnet/minecraft/advancements/AdvancementHolder;)V"),
            remap = false
    )
    private void addConditionsToResult(RecipeOutput output, ResourceLocation id, Recipe<?> recipe, AdvancementHolder advancementHolder, Operation<Void> original) {
        Conditional.with(recipe, Conditional.of(this).get());
        original.call(output, id, recipe, advancementHolder);
    }

}
