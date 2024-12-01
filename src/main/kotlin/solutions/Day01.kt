package solutions

import solve
import test
import tokenisedToInt
import transposed
import kotlin.math.abs


fun main() {
    val day = 1
    val test01Expected = 11
    val test02Expected = 31

    fun part1(input: List<String>): Int = LocationLists(input).sumOfDifferences()
    fun part2(input: List<String>): Int = LocationLists(input).sumOfProducts()
    test(day, ::part1, test01Expected, ::part2, test02Expected)
    solve(day, ::part1, ::part2)
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





