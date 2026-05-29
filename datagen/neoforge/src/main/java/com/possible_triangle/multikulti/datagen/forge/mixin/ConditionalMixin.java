package com.possible_triangle.multikulti.datagen.forge.mixin;

import com.possible_triangle.multikulti.datagen.conditions.ConditionHolder;
import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import com.possible_triangle.multikulti.datagen.conditions.IConditionHolder;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipe;
import net.minecraft.advancements.Advancement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(
        remap = false,
        value = {
                ProcessingRecipe.class,
                SequencedAssemblyRecipe.class,
                Advancement.class,
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
