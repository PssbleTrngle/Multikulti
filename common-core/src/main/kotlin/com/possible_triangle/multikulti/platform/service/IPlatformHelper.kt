package com.possible_triangle.multikulti.platform.service

interface IPlatformHelper {

    fun isDev(): Boolean

    fun isModLoaded(modId: String): Boolean

}