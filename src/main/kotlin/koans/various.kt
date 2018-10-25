package koans

import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.*

/**
 * Key represents month, value is list of days in month when there's a holiday
 *
 * For instance:
 * September: 1st, 15th
 * October: 30th
 * November: 1st
 *
 * etc.
 */
typealias HolidaysPerMonths = Map<Int, List<Int>>

/**
 * Implement this extension function on Date, which will determine whether it is a holiday
 */
fun Date.isHoliday(holidaysPerMonths: HolidaysPerMonths): Boolean {
    val localDate = LocalDate.from(this.toInstant().atZone(ZoneId.systemDefault()))

    return holidaysPerMonths[localDate.monthValue]?.contains(localDate.dayOfMonth) ?: false
}

fun main(args: Array<String>) {
    val date = Date.from(LocalDate.of(2018, 9, 1).atStartOfDay().toInstant(ZoneOffset.UTC))
    val holidaysPerMonths = mapOf(
            Pair(9, listOf(1, 15)), Pair(10, listOf(30))
    )

    // This should return true
    println(date.isHoliday(holidaysPerMonths))
}