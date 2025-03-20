package com.possible_triangle.multikulti.registrate.provider

import com.possible_triangle.multikulti.registrate.platform.service.RegistrateProviders
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.providers.RegistrateProvider
import net.minecraft.data.PackOutput
import net.minecraft.sounds.SoundEvent
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.common.data.SoundDefinition
import net.minecraftforge.common.data.SoundDefinitionsProvider
import net.minecraftforge.fml.LogicalSide

class ForgeRegistrateSoundProvider(
    private val owner: AbstractRegistrate<*>,
    output: PackOutput,
    helper: ExistingFileHelper
) : SoundDefinitionsProvider(output, owner.modid, helper), RegistrateSoundsProvider, RegistrateProvider {

    override fun getSide() = LogicalSide.CLIENT

    override fun registerSounds() {
        owner.genData(RegistrateProviders.INSTANCE.sounds, this)
    }

    override fun register(
        sound: SoundEvent,
        definition: SoundDefinition
    ) = add(sound, definition)
}