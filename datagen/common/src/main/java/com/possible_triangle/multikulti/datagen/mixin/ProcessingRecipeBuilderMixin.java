package com.possible_triangle.multikulti.datagen.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.possible_triangle.multikulti.datagen.conditions.ConditionHolder;
import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import com.possible_triangle.multikulti.datagen.conditions.IConditionHolder;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipeBuilder;
import java.util.function.Consumer;
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
            method = "build(Ljava/util/function/Consumer;)V",
            at = @At(value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V"),
            remap = false
    )
    private void addConditionsToResult(Consumer<?> consumer, Object object, Operation<Void> original) {
        Conditional.with(object, Conditional.of(this).get());
        original.call(consumer, object);
    }

}
