package solutions

import kotlin.io.path.Path
import kotlin.io.path.readLines

abstract class Solution {
    abstract val day: Int
    open fun part1(input: List<String>): Int = 0
    open fun part2(input: List<String>): Int = 0

    private fun inputFileName(): String = "%02d".format(day) + ".txt"
    private fun testFileName(): String = "%02d".format(day) + "_test.txt"
    private fun input(): List<String> = Path("src/main/kotlin/input/" + inputFileName()).readLines()
    private fun testInput(): List<String> = Path("src/main/kotlin/input/" + testFileName()).readLines()

    private fun testPart1(expectedResult: Int): Boolean {
        val result = part1(testInput())
        println("Test 1: $result")
        if (result != expectedResult) {
            println( "...but expected $expectedResult")
            println("*************")
        }
        return (result == expectedResult)
    }

    private fun testPart2(expectedResult: Int): Boolean {
        val result = part2(testInput())
        println("Test 2: $result")
        if (result != expectedResult) {
            println( "... but expected $expectedResult")
            println("*************")
        }
        return (result == expectedResult)
    }

    fun test(expectedResult1: Int, expectedResult2: Int): Solution? {
        println("*************")
        if (testPart1(expectedResult1)) {
            return if (testPart2(expectedResult2)) this else null
        }
        return null
    }

    fun solve() {
        println("*************")
        println("Part 1: " + part1(input()))
        println("Part 2: " + part2(input()))
        println("*************")
        return
    }
}