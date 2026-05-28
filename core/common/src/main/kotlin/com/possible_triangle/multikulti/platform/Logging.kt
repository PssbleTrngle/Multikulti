package com.possible_triangle.multikulti.platform

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

val LOGGER: Logger = LogManager.getLogger("Multikulti")

fun Logger.dev(
    message: String,
    vararg params: Any,
) {
    if (Services.PLATFORM.isDev()) {
        info(message, *params)
    }
}
