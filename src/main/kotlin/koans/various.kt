package koans

import java.time.LocalDate
import java.time.ZoneOffset
import java.util.*

/**
 * Key represents month, value is list of days in month when there's a holiday
 */
typealias HolidaysPerMonths = Map<Int, List<Int>>

/**
 * Implement this extension function on Date, which will determine whether it is a holiday
 */
fun Date.isHoliday(holidaysPerMonths: HolidaysPerMonths): Boolean = TODO()

fun main(args: Array<String>) {
    val date = Date.from(LocalDate.of(2018, 9, 1).atStartOfDay().toInstant(ZoneOffset.UTC))
    val holidaysPerMonths = mapOf(
            Pair(9, listOf(1, 15)), Pair(10, listOf(30))
    )

    println(date.isHoliday(holidaysPerMonths))
}