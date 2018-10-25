package koans

import java.lang.IllegalArgumentException
import kotlin.math.exp

/**
 * Implement and rewrite the following example:
 *
 * 1. Use smart casting to implement evaluation of expression
 *  - if expression is Num, return its value
 *  - if expression is Sum, sum of its evaluated left and right sides
 *
 * See: https://kotlinlang.org/docs/reference/typecasts.html#smart-casts
 *
 * 2. Try to avoid having `else` branch in when expression by using
 *    sealed class as Expr instead of interface.
 *
 * See: https://kotlinlang.org/docs/reference/sealed-classes.html
 */

fun eval(expr: Expr): Int =
        when (expr) {
            is Num -> expr.value
            is Sum -> eval(expr.left) + eval(expr.right)
        }

sealed class Expr
class Num(val value: Int) : Expr()
class Sum(val left: Expr, val right: Expr) : Expr()

fun main(args: Array<String>) {
    println(eval(Num(1)) == 1)
    println(eval(Sum(Num(2), Num(3))) == 5)
}