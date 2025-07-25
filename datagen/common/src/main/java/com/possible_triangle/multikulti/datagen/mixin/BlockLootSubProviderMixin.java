package com.possible_triangle.multikulti.datagen.mixin;

import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockLootSubProvider.class)
public class BlockLootSubProviderMixin {

    @Inject(
            method = "add(Lnet/minecraft/world/level/block/Block;Lnet/minecraft/world/level/storage/loot/LootTable$Builder;)V",
            at = @At("HEAD")
    )
    private void transferConditions(Block block, LootTable.Builder value, CallbackInfo ci) {
        Conditional.with(value, Conditional.of(this).get());
    }

}
