package solutions

import tokenisedToInt
import kotlin.math.abs

fun main() {
    Day02().test(2, 4)?.solve()
}

class Day02 : Solution() {
    override val day: Int = 2
    override fun part1(input: List<String>): Int = Reports(input).validCount

    override fun part2(input: List<String>): Int = Reports(input).dampenedValidCount
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





