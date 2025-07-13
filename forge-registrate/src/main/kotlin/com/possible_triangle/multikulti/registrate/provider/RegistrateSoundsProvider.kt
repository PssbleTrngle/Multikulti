package com.possible_triangle.multikulti.registrate.provider

import com.possible_triangle.multikulti.registrate.platform.service.ForgeRegistrateBuilders
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.providers.RegistrateProvider
import net.minecraft.data.PackOutput
import net.minecraft.sounds.SoundEvent
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.common.data.SoundDefinition
import net.minecraftforge.common.data.SoundDefinitionsProvider
import net.minecraftforge.fml.LogicalSide

class RegistrateSoundsProvider(
    private val owner: AbstractRegistrate<*>,
    output: PackOutput,
    helper: ExistingFileHelper
) : SoundDefinitionsProvider(output, owner.modid, helper), RegistrateProvider {

    override fun getSide() = LogicalSide.CLIENT

    override fun registerSounds() {
        owner.genData(ForgeRegistrateBuilders.SOUNDS, this)
    }

    public override fun add(sound: SoundEvent, definition: SoundDefinition) = super.add(sound, definition)

}