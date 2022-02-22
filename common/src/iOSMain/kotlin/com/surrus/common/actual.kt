package com.surrus.common

import platform.Foundation.NSCalendar
import platform.Foundation.NSCalendarUnitHour
import platform.Foundation.NSCalendarUnitMinute
import platform.Foundation.NSDate

actual fun timestamp(): String {
    val dateNow = NSDate()
    val calendar = NSCalendar.currentCalendar()
    val date = calendar.components(NSCalendarUnitHour or NSCalendarUnitMinute , fromDate = dateNow)
    return "${date.hour} : ${date.minute}"

}
