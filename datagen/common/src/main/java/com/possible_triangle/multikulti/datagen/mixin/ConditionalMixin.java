package com.possible_triangle.multikulti.datagen.mixin;

import com.possible_triangle.multikulti.datagen.conditions.ConditionHolder;
import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import com.possible_triangle.multikulti.datagen.conditions.IConditionHolder;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import net.minecraft.world.item.crafting.SmithingTrimRecipe;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(
        remap = false,
        value = {
                RecipeProvider.class,
                LootTable.class,
                LootTableProvider.class,
                BlockLootSubProvider.class,
                SmithingTrimRecipe.class,
                SmithingTransformRecipe.class,
                SingleItemRecipe.class,
                ShapedRecipe.class,
                ShapelessRecipe.class,
                AbstractCookingRecipe.class
        }
)
public class ConditionalMixin implements Conditional {

    @Unique
    private final IConditionHolder multikulti$conditions = new ConditionHolder();

    @Override
    public IConditionHolder multikulti$conditions() {
        return multikulti$conditions;
    }

}
