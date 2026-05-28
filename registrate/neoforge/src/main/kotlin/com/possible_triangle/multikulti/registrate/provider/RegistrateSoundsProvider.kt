package com.possible_triangle.multikulti.registrate.provider

import com.possible_triangle.multikulti.registrate.platform.service.ForgeRegistrateBuilders
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.providers.RegistrateProvider
import net.minecraft.data.PackOutput
import net.minecraft.sounds.SoundEvent
import net.neoforged.fml.LogicalSide
import net.neoforged.neoforge.common.data.ExistingFileHelper
import net.neoforged.neoforge.common.data.SoundDefinition
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider

class RegistrateSoundsProvider(
    private val owner: AbstractRegistrate<*>,
    output: PackOutput,
    helper: ExistingFileHelper,
) : SoundDefinitionsProvider(output, owner.modid, helper),
    RegistrateProvider {
    override fun getSide() = LogicalSide.CLIENT

    override fun registerSounds() {
        owner.genData(ForgeRegistrateBuilders.SOUNDS, this)
    }

    public override fun add(
        sound: SoundEvent,
        definition: SoundDefinition,
    ) = super.add(sound, definition)
}
