package com.possible_triangle.multikulti.registrate.provider

import com.possible_triangle.multikulti.registrate.platform.service.RegistrateProviders
import com.tterrag.registrate.AbstractRegistrate
import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper
import io.github.fabricators_of_create.porting_lib.data.SoundDefinition
import io.github.fabricators_of_create.porting_lib.data.SoundDefinitionsProvider
import net.fabricmc.api.EnvType
import net.minecraft.data.PackOutput
import net.minecraft.sounds.SoundEvent

class FabricRegistrateSoundProvider(
    private val owner: AbstractRegistrate<*>,
    output: PackOutput,
    helper: ExistingFileHelper
) : SoundDefinitionsProvider(output, owner.modid, helper), RegistrateSoundsProvider {

    override fun getSide() = EnvType.CLIENT

    override fun register(sound: SoundEvent, definition: SoundDefinition) = add(sound, definition)

    override fun registerSounds() {
        owner.genData(RegistrateProviders.INSTANCE.sounds, this)
    }

}