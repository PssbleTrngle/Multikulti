package com.possible_triangle.multikulti.mixin;

import com.possible_triangle.multikulti.datagen.conditions.ConditionHolder;
import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import net.minecraft.data.recipes.RecipeProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = {
        RecipeProvider.class,
}, targets = {
        "net.minecraft.data.recipes.CraftingRecipeBuilder$CraftingResult"
})
public class RecipeProviderMixin implements Conditional {

    @Unique
    private final ConditionHolder multikulti$conditions = new ConditionHolder();

    @Override
    public ConditionHolder multikulti$conditions() {
        return multikulti$conditions;
    }

}
