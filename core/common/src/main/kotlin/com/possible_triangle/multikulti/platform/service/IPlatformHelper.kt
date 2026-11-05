package com.possible_triangle.multikulti.platform.service

import com.possible_triangle.multikulti.Loader

interface IPlatformHelper {
    val loader: Loader

    fun isDev(): Boolean

    fun isModLoaded(modId: String): Boolean
}
