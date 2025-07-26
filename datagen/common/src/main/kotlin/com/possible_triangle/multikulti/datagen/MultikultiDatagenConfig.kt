package com.possible_triangle.multikulti.datagen

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import net.minecraft.client.Minecraft
import java.io.File
import java.nio.file.Files


data class MultikultiDatagenConfig(
    val targets: Collection<Loader>
) {

    enum class Loader {
        FORGE, FABRIC
    }

    companion object {
        private val DEFAULT = MultikultiDatagenConfig(
            targets = Loader.entries,
        )

        private val GSON: Gson = GsonBuilder()
            .setPrettyPrinting()
            .setLenient()
            .create()

        val INSTANCE by lazy { load() }

        private fun getFile(): File {
            val configFolder = File("config")
            Files.createDirectories(configFolder.toPath())
            return configFolder.resolve("multikulti-datagen.json")
        }

        private fun createDefault(): MultikultiDatagenConfig {
            return DEFAULT.apply { save() }
        }

        private fun load(): MultikultiDatagenConfig {
            val file = getFile();
            if (!file.exists()) return createDefault()
            return try {
                GSON.fromJson(file.readText(), MultikultiDatagenConfig::class.java)
            } catch (ex: JsonSyntaxException) {
                createDefault()
            }
        }

        private fun MultikultiDatagenConfig.save() {
            val file = getFile()
            val json = GSON.toJson(this)
            file.writeText(json)
        }

    }

}