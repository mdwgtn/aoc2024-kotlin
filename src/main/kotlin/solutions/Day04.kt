package solutions

import TwoDMap
import solve
import test

fun main() {
    val day = 4
    val test01Expected = 18
    val test02Expected = 9

    fun part1(input: List<String>): Int = XmasCount(input).part1()
    fun part2(input: List<String>): Int = XmasCount(input).part2()

    test(day, ::part1, test01Expected, ::part2, test02Expected)
    solve(day, ::part1, ::part2)
}

class XmasCount(input: List<String>) {
    val map = TwoDMap(input)
    fun part1(): Int {
        return map.countOfLinearSequences("XMAS")
    }
    fun part2(): Int {
        return map.countOfXWords("SAM")
    }
}
