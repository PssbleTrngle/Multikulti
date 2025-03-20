package com.possible_triangle.multikulti.registrate.platform.service

import com.possible_triangle.multikulti.platform.Services
import com.possible_triangle.multikulti.registrate.provider.RegistrateSoundsProvider
import com.tterrag.registrate.providers.ProviderType

interface RegistrateProviders {

    companion object {
        @JvmStatic
        val INSTANCE = Services.load(RegistrateProviders::class)
    }

    val sounds: ProviderType<RegistrateSoundsProvider>

}