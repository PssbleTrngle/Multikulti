package com.possible_triangle.multikulti.datagen

import com.possible_triangle.multikulti.Loader
import com.possible_triangle.multikulti.platform.Services

object MultikultiDatagenConfig {
    private val loaders by lazy {
        val property: String? = System.getProperty("multikulti.datagen.loaders")
        val keys =
            property
                ?.split(",")
                ?.map { it.trim() }
                ?.mapNotNull {
                    try {
                        Loader.valueOf(it)
                    } catch (_: IllegalArgumentException) {
                        null
                    }
                } ?: emptyList()

        keys.ifEmpty {
            listOf(
                Services.PLATFORM.loader,
            )
        }
    }

    fun isEnabled(loader: Loader) = loaders.contains(loader)
}
