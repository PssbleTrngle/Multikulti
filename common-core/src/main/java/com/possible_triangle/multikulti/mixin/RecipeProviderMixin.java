package com.possible_triangle.multikulti.mixin;

import com.possible_triangle.multikulti.platform.conditions.Condition;
import net.minecraft.data.recipes.RecipeProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Mixin(RecipeProvider.class)
public class RecipeProviderMixin {

    @Unique
    private final Set<Condition> multikulti$conditions = new HashSet<>();

    @Override
    public void multikulti$addCondition(Condition condition) {
        multikulti$conditions.add(condition);
    }

    @Override
    public Collection<Condition> push$conditions() {
        return multikulti$conditions;
    }

}
