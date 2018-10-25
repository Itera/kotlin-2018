package koans

/**
 * First assignment
 *
 * Implement correctly methods and interfaces required for the code in `main` method to compile.
 *
 *  1. Implement `kotlin.Comparable<InvoicingPeriod>` that will compare the periods (by month/year time)
 *  2. Implement `inc()` operator in order to increment period by one month
 *  3. Lastly, implement simple validation using `init {}` intializer that will throw IllegalArgumentException when
 *     parameter month is not within range of <1, 12> and year is less than 1970 :)
 *
 *  Try to "think" in Kotlin and use as less constructs as needed (e.g. make us of type inference, lambdas, etc.)
 *
 *  You can use these parts of documentation:
 *  - https://kotlinlang.org/docs/reference/interfaces.html#implementing-interfaces
 *  - https://kotlinlang.org/docs/reference/operator-overloading.html#increments-and-decrements
 *  - https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/require.html#require
 *
 * Note: This is the first assignment, since these classes are used in the `collections.kt` assignment.
 */

data class InvoicingPeriod(val month: Int, val year: Int) : Comparable<InvoicingPeriod> {
    init {
        require(month in 1..12 && year >= 1970)
    }

    override fun compareTo(other: InvoicingPeriod): Int = if (this.year == other.year) this.month - other.month else this.year - other.year
    operator fun inc(): InvoicingPeriod = when (month) {
        12 -> InvoicingPeriod(1, year + 1)
        else -> InvoicingPeriod(month + 1, year)
    }
}

data class Invoice(val price: Int, val paid: Boolean, val invoicingPeriod: InvoicingPeriod)
data class Customer(val name: String, val projects: List<Project>)
data class Project(val name: String, val invoices: List<Invoice>)

fun main(args: Array<String>) {
//    TODO() // This should be commented out when above is implemented
    // Uncomment and run. All of these should return true (or pass without exception)
    println("First part:")
    println(InvoicingPeriod(1, 2018) < InvoicingPeriod(10, 2018))
    println(InvoicingPeriod(1, 2018) == InvoicingPeriod(1, 2018))
    println(InvoicingPeriod(10, 2018) > InvoicingPeriod(1, 2018))
    println(InvoicingPeriod(5, 2018) in InvoicingPeriod(1, 2018)..InvoicingPeriod(12, 2018))

    println("Second part:")
    var start = InvoicingPeriod(12, 2018)
    println(++start == InvoicingPeriod(1, 2019))
    println(++start == InvoicingPeriod(2, 2019))

    println("Third part")
    try {
        println(InvoicingPeriod(-5, 2018))
    } catch (illegalArgumentException: IllegalArgumentException) {
        println("You've done it right!")
    }
}