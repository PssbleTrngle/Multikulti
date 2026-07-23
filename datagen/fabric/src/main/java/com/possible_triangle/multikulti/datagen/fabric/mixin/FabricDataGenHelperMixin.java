package com.possible_triangle.multikulti.datagen.fabric.mixin;

import static net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions.CONDITIONS_KEY;

import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.impl.datagen.FabricDataGenHelper;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FabricDataGenHelper.class)
public class FabricDataGenHelperMixin {

    @WrapMethod(method = "addConditions(Lcom/google/gson/JsonObject;[Lnet/fabricmc/fabric/api/resource/conditions/v1/ResourceCondition;)V")
    private static void combineConditions(JsonObject json, ResourceCondition[] conditions, Operation<Void> original) {
        var additional = new JsonObject();
        original.call(additional, conditions);

        if (additional.has(CONDITIONS_KEY)) {
            if (json.has(CONDITIONS_KEY)) {
                var array = json.getAsJsonArray(CONDITIONS_KEY);
                var additionalArray = additional.getAsJsonArray(CONDITIONS_KEY);
                array.addAll(additionalArray);
            } else {
                json.add(CONDITIONS_KEY, additional.get(CONDITIONS_KEY));
            }
        }
    }

}
