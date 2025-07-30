package com.possible_triangle.multikulti.datagen.forge.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.possible_triangle.multikulti.datagen.conditions.ConditionHolder;
import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import com.possible_triangle.multikulti.datagen.conditions.IConditionHolder;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipeBuilder;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.common.conditions.ICondition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;

@Mixin(
        remap = false,
        value = {
                ProcessingRecipeBuilder.class,
                SequencedAssemblyRecipeBuilder.class
        }
)
public class ProcessingRecipeBuilderMixin implements Conditional {

    @Unique
    private final IConditionHolder multikulti$conditions = new ConditionHolder();

    @Override
    public IConditionHolder multikulti$conditions() {
        return multikulti$conditions;
    }

    @Coerce
    @WrapOperation(
            method = "build(Lnet/minecraft/data/recipes/RecipeOutput;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/data/recipes/RecipeOutput;accept(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/world/item/crafting/Recipe;Lnet/minecraft/advancements/AdvancementHolder;[Lnet/neoforged/neoforge/common/conditions/ICondition;)V"),
            remap = false
    )
    private void addConditionsToResult(RecipeOutput output, ResourceLocation id, Recipe<?> recipe, AdvancementHolder advancementHolder, ICondition[] conditions, Operation<Void> original) {
        Conditional.with(recipe, Conditional.of(this).get());
        original.call(output, id, recipe, advancementHolder, conditions);
    }

}
