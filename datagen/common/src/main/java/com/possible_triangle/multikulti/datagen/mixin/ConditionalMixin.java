package com.possible_triangle.multikulti.datagen.mixin;

import com.possible_triangle.multikulti.datagen.conditions.ConditionHolder;
import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import com.possible_triangle.multikulti.datagen.conditions.IConditionHolder;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.data.recipes.SmithingTrimRecipeBuilder;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = {
        RecipeProvider.class,
        LootTable.class,
        LootTableProvider.class,
        BlockLootSubProvider.class,
        SmithingTrimRecipeBuilder.Result.class,
        SmithingTransformRecipeBuilder.Result.class,
        SingleItemRecipeBuilder.Result.class,
}, targets = {
        "net.minecraft.data.recipes.CraftingRecipeBuilder$CraftingResult",
        "net.minecraft.data.recipes.SimpleCookingRecipeBuilder$Result"
})
public class ConditionalMixin implements Conditional {

    @Unique
    private final IConditionHolder multikulti$conditions = new ConditionHolder();

    @Override
    public IConditionHolder multikulti$conditions() {
        return multikulti$conditions;
    }

}
