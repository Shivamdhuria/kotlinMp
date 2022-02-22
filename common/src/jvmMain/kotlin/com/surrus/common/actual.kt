package com.surrus.common

import java.time.LocalTime


actual fun timestamp(): String {
    val instant = LocalTime.now()
    return "${instant.hour} : ${instant.minute}"
}
