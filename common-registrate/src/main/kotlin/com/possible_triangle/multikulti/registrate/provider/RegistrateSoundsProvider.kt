package com.possible_triangle.multikulti.registrate.provider

import com.tterrag.registrate.providers.RegistrateProvider
import io.github.fabricators_of_create.porting_lib.data.SoundDefinition
import net.minecraft.sounds.SoundEvent

interface RegistrateSoundsProvider : RegistrateProvider {

    fun register(sound: SoundEvent, definition: SoundDefinition)

}