package com.possible_triangle.multikulti.mixin;

import com.possible_triangle.multikulti.platform.conditions.Condition;
import com.possible_triangle.multikulti.platform.conditions.Conditional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.data.recipes.RecipeProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(RecipeProvider.class)
public class RecipeProviderMixin implements Conditional {

    @Unique
    private final Set<Condition> multikulti$conditions = new HashSet<>();

    @Override
    public void multikulti$addCondition(Condition condition) {
        multikulti$conditions.add(condition);
    }

    @Override
    public Collection<Condition> multikulti$conditions() {
        return multikulti$conditions;
    }

}
