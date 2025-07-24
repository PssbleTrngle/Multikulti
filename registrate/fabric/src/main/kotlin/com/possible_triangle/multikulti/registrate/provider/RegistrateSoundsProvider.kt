package com.possible_triangle.multikulti.registrate.provider

import com.possible_triangle.multikulti.registrate.platform.service.FabricRegistrateBuilders
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.providers.RegistrateProvider
import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper
import io.github.fabricators_of_create.porting_lib.data.SoundDefinition
import io.github.fabricators_of_create.porting_lib.data.SoundDefinitionsProvider
import net.fabricmc.api.EnvType
import net.minecraft.data.PackOutput
import net.minecraft.sounds.SoundEvent

class RegistrateSoundsProvider(
    private val owner: AbstractRegistrate<*>,
    output: PackOutput,
    helper: ExistingFileHelper
) : SoundDefinitionsProvider(output, owner.modid, helper), RegistrateProvider {

    override fun getSide() = EnvType.CLIENT

    public override fun add(sound: SoundEvent, definition: SoundDefinition) = super.add(sound, definition)

    override fun registerSounds() {
        owner.genData(FabricRegistrateBuilders.SOUNDS, this)
    }

}