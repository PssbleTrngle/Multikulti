package com.possible_triangle.multikulti.registrate.platform.service

import com.possible_triangle.multikulti.registrate.provider.FabricRegistrateSoundProvider
import com.possible_triangle.multikulti.registrate.provider.RegistrateSoundsProvider
import com.tterrag.registrate.providers.ProviderType

class ForgeRegistrateProviders : RegistrateProviders {

    override val sounds: ProviderType<RegistrateSoundsProvider> = ProviderType.register("sounds") { owner, context ->
        FabricRegistrateSoundProvider(owner, context.output, context.helper)
    }

}