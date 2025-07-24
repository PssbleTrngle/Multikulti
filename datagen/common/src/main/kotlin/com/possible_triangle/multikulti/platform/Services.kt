package com.possible_triangle.multikulti.platform

import com.possible_triangle.multikulti.platform.service.IPlatformHelper
import java.util.Optional
import java.util.ServiceLoader
import kotlin.jvm.optionals.getOrNull
import kotlin.reflect.KClass

object Services {

    @JvmStatic
    val PLATFORM = load(IPlatformHelper::class)

    fun <T : Any> loadOptional(clazz: KClass<T>) = loadOptional(clazz.java).getOrNull()

    fun <T : Any> load(clazz: KClass<T>) = load(clazz.java)

    @JvmStatic
    fun <T> loadOptional(clazz: Class<T>): Optional<T> {
        return ServiceLoader.load(clazz).findFirst()
    }

    @JvmStatic
    fun <T> load(clazz: Class<T>): T {
        return loadOptional(clazz).orElseThrow {
            NullPointerException("Failed to load service for ${clazz.name}")
        }
    }

}