package com.possible_triangle.multikulti.test

import net.minecraft.SharedConstants
import net.minecraft.Util
import net.minecraft.data.registries.VanillaRegistries
import net.minecraft.server.Bootstrap
import net.minecraftforge.data.event.GatherDataEvent.DataGeneratorConfig
import net.minecraftforge.fml.ModLoader
import net.minecraftforge.fml.ModWorkManager
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInfo
import java.util.concurrent.CompletableFuture
import kotlin.io.path.Path

open class DatagenTest {

    private lateinit var dataGeneratorConfig: DataGeneratorConfig

    companion object {
        @JvmStatic
        @BeforeAll
        fun initializeMods() {
            SharedConstants.tryDetectVersion()
            Bootstrap.bootStrap()
            ModLoader.get().gatherAndInitializeMods(ModWorkManager.syncExecutor(), ModWorkManager.parallelExecutor()) {}
        }
    }

    @BeforeEach
    fun setupDataGen(info: TestInfo) {
        val testPath = info.displayName.replace("/\\w+/".toRegex(), "-").lowercase()
        val out = Path("src/generated/$testPath")

        val lookupProvider = CompletableFuture.supplyAsync(VanillaRegistries::createLookup, Util.backgroundExecutor())
        dataGeneratorConfig = DataGeneratorConfig(
            setOf("forge"),
            out,
            emptyList(),
            lookupProvider,
            true,
            true,
            true,
            true,
            true,
            false
        )
    }

    fun runDataGen() {
        dataGeneratorConfig.runAll()
    }

}