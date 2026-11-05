@file:JvmName("Logging")

package com.possible_triangle.multikulti

import com.possible_triangle.multikulti.platform.Services
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@JvmField
val LOGGER: Logger = LogManager.getLogger("Multikulti")

fun Logger.dev(
    message: String,
    vararg params: Any,
) {
    if (Services.PLATFORM.isDev()) {
        info(message, *params)
    }
}
