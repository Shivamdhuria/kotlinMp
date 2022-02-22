package com.surrus.common.repository

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
actual fun timestamp(): String {
    val instant = LocalTime.now()
    return "${instant.hour} : ${instant.minute}"
}
