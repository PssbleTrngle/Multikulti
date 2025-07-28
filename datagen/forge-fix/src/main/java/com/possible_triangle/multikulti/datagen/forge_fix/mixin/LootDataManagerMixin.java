package com.possible_triangle.multikulti.datagen.forge_fix.mixin;

import com.google.gson.JsonElement;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import java.util.Optional;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.storage.loot.LootDataManager;
import net.minecraft.world.level.storage.loot.LootDataType;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = LootDataManager.class, remap = false)
public class LootDataManagerMixin {

    @Unique
    private static final ICondition.IContext multikulti$context = ICondition.IContext.TAGS_INVALID;

    @Unique
    private static final Logger multikulti$logger = LoggerFactory.getLogger(LootDataManager.class);

    @Unique
    private static boolean multikulti$check(JsonElement json, ResourceLocation id) {
        if (!json.isJsonObject()) return true;
        try {
            return CraftingHelper.processConditions(json.getAsJsonObject(), "conditions", multikulti$context);
        } catch (Exception ex) {
            multikulti$logger.debug("Error checking conditions for loot table {}", id, ex);
            return true;
        }
    }

    @WrapOperation(
            method = "lambda$scheduleElementParse$4(Lnet/minecraft/world/level/storage/loot/LootDataType;Lnet/minecraft/server/packs/resources/ResourceManager;Ljava/util/Map;Lnet/minecraft/resources/ResourceLocation;Lcom/google/gson/JsonElement;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/loot/LootDataType;deserialize(Lnet/minecraft/resources/ResourceLocation;Lcom/google/gson/JsonElement;Lnet/minecraft/server/packs/resources/ResourceManager;)Ljava/util/Optional;")
    )
    private static Optional<LootTable> respectConditions(LootDataType<?> instance, ResourceLocation id, JsonElement json, ResourceManager manager, Operation<Optional<LootTable>> original) {
        if (instance != LootDataType.TABLE || multikulti$check(json, id)) {
            return original.call(instance, id, json, manager);
        } else {
            multikulti$logger.debug("Skipping loading loot table {} as it's conditions were not met", id);
            return Optional.empty();
        }
    }

}
