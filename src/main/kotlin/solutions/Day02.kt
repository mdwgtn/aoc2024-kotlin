package solutions

import solve
import test
import tokenisedToInt
import kotlin.math.abs

fun main() {
    val day = 2
    val test01Expected = 2
    val test02Expected = 4

    fun part1(input: List<String>): Int = Reports(input).validCount
    fun part2(input: List<String>): Int = Reports(input).dampenedValidCount

    test(day, ::part1, test01Expected, ::part2, test02Expected)
    solve(day, ::part1, ::part2)
}

class Report(private val input: List<Int>) {

    fun isValid(): Boolean = isMonotonic() && isValidDelta()

    fun isValidWhenDampened(): Boolean = input.indices
        .map { i -> Report(input.filterIndexed { index, _ -> index != i }) }
        .any { it.isValid() }

    private fun isMonotonic(): Boolean = input.zipWithNext()
        .all { (curr, next) -> if (input[0] < input[1]) curr < next else next < curr }

    private fun isValidDelta(): Boolean = input.zipWithNext()
        .all { (curr, next) -> abs(curr - next) in 1..3 }
}

class Reports(input: List<String>) {
    private val tokenisedInput = input.tokenisedToInt()
    private val reports = tokenisedInput.map { Report(it) }
    val validCount = reports.count { it.isValid() }
    val dampenedValidCount = reports.count { it.isValidWhenDampened() }
}





