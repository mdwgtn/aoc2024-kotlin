package solutions

import tokenisedToInt
import transposed
import kotlin.math.abs

fun main() {
    Day01().test(11, 31)?.solve()
}

class Day01 : Solution() {
    override val day: Int = 1
    override fun part1(input: List<String>): Int = LocationLists(input).sumOfDifferences()

    override fun part2(input: List<String>): Int = LocationLists(input).sumOfProducts()
}

class LocationLists(input: List<String>) {
    private val lists = input.tokenisedToInt().transposed()
    private val sortedLists = lists.map { it.sorted() }

    fun sumOfDifferences(): Int =
        sortedLists[0]
            .zip(sortedLists[1])
            .sumOf { abs(it.first - it.second) }

    fun sumOfProducts(): Int =
        sortedLists[0]
            .sumOf { l ->
                l * sortedLists[1]
                    .count { r -> r == l }
            }
}
