package com.possible_triangle.multikulti.platform

import com.possible_triangle.multikulti.platform.service.IPlatformHelper
import java.util.*

object Services {
    @JvmField
    val PLATFORM = load(IPlatformHelper::class.java)

    @JvmStatic
    fun <T> loadOptional(clazz: Class<T>): Optional<T> {
        val classLoader = Services::class.java.classLoader
        return ServiceLoader.load(clazz, classLoader).findFirst()
    }

    @JvmStatic
    fun <T> load(clazz: Class<T>): T =
        loadOptional(clazz).orElseThrow {
            NullPointerException("Failed to load service for ${clazz.name}")
        }
}
