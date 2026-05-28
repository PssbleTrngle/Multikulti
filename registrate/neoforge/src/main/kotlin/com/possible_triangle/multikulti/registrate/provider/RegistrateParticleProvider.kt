package com.possible_triangle.multikulti.registrate.provider

import com.possible_triangle.multikulti.registrate.platform.service.ForgeRegistrateBuilders
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.providers.RegistrateProvider
import net.minecraft.core.particles.ParticleType
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.neoforged.fml.LogicalSide
import net.neoforged.neoforge.common.data.ExistingFileHelper
import net.neoforged.neoforge.common.data.ParticleDescriptionProvider

class RegistrateParticleProvider(
    private val owner: AbstractRegistrate<*>,
    output: PackOutput,
    helper: ExistingFileHelper,
) : ParticleDescriptionProvider(output, helper),
    RegistrateProvider {
    override fun getSide() = LogicalSide.CLIENT

    override fun addDescriptions() {
        owner.genData(ForgeRegistrateBuilders.PARTICLES, this)
    }

    fun spriteSet(
        type: ParticleType<*>,
        textures: Collection<ResourceLocation>,
    ) = super.spriteSet(type, textures)
}
