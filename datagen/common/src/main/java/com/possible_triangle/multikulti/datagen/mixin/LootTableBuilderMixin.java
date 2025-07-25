package com.possible_triangle.multikulti.datagen.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.possible_triangle.multikulti.datagen.conditions.ConditionHolder;
import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import com.possible_triangle.multikulti.datagen.conditions.IConditionHolder;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LootTable.Builder.class)
public class LootTableBuilderMixin implements Conditional {

    @Unique
    private final IConditionHolder multikulti$conditions = new ConditionHolder();

    @Override
    public IConditionHolder multikulti$conditions() {
        return multikulti$conditions;
    }

    @ModifyReturnValue(
            method = "build",
            at = @At("RETURN")
    )
    private LootTable addConditions(LootTable original) {
        return Conditional.with(original, Conditional.of(this).get());
    }

}
