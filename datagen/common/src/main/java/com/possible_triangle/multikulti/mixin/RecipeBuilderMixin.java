package com.possible_triangle.multikulti.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.possible_triangle.multikulti.platform.conditions.ConditionHolder;
import com.possible_triangle.multikulti.platform.conditions.Conditional;
import java.util.function.Consumer;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.data.recipes.SmithingTrimRecipeBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;

@Mixin({
        ShapedRecipeBuilder.class,
        ShapelessRecipeBuilder.class,
        SimpleCookingRecipeBuilder.class,
        SingleItemRecipeBuilder.class,
        SmithingTransformRecipeBuilder.class,
        SmithingTrimRecipeBuilder.class
})
public class RecipeBuilderMixin implements Conditional {

    @Unique
    private final ConditionHolder multikulti$conditions = new ConditionHolder();

    @Override
    public ConditionHolder multikulti$conditions() {
        return multikulti$conditions;
    }

    @Coerce
    @WrapOperation(
            method = "save(Ljava/util/function/Consumer;Lnet/minecraft/resources/ResourceLocation;)V",
            at = @At(value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V"),
            remap = false
    )
    private void addConditionsToResult(Consumer<?> consumer, Object object, Operation<Void> original) {
        Conditional.with(object, Conditional.of(this).get());
        original.call(consumer, object);
    }

}
