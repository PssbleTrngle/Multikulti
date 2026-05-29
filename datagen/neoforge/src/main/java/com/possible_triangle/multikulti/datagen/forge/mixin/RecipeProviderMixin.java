package com.possible_triangle.multikulti.datagen.forge.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import com.possible_triangle.multikulti.datagen.forge.NeoforgeConditionExtender;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.common.conditions.ICondition;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = RecipeProvider.class, remap = false)
public class RecipeProviderMixin {

    @WrapOperation(
        method = "run(Lnet/minecraft/data/CachedOutput;Lnet/minecraft/core/HolderLookup$Provider;)Ljava/util/concurrent/CompletableFuture;",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/data/recipes/RecipeProvider;buildRecipes(Lnet/minecraft/data/recipes/RecipeOutput;Lnet/minecraft/core/HolderLookup$Provider;)V")
    )
    private void serializeConditions(RecipeProvider instance, RecipeOutput output, HolderLookup.Provider provider, Operation<Void> original) {
        original.call(instance, new RecipeOutput() {
            @Override
            public Advancement.Builder advancement() {
                return output.advancement();
            }

            @Override
            public void accept(ResourceLocation id, Recipe<?> recipe, @Nullable AdvancementHolder advancementHolder, ICondition... conditions) {
                output.accept(id, recipe, copyConditions(recipe, advancementHolder), NeoforgeConditionExtender.extend(Conditional.merge(recipe, instance), conditions));
            }
        }, provider);
    }

    @Unique
    private static AdvancementHolder copyConditions(Recipe<?> from, @Nullable AdvancementHolder to) {
        if (to == null) return to;
        var conditions = Conditional.of(from).get();
        if (conditions.isEmpty()) return to;

        var advancement = Conditional.with(to.value(), conditions);
        return new AdvancementHolder(to.id(), advancement);
    }

}
