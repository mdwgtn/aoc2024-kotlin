package solutions

import solve
import test

fun main() {
    val day = 3
    val test01Expected = 161
    val test02Expected = 48

    fun part1(input: List<String>): Int = MulProgram(input).sumOfMuls()
    fun part2(input: List<String>): Int = MulProgram(input).sumOfDoMuls()

    test(day, ::part1, test01Expected, ::part2, test02Expected)
    solve(day, ::part1, ::part2)
}

class MulProgram(input: List<String>) {
    private val normalisedProgram = "do()" + input.joinToString("")
    private val mulPattern = Regex("""mul\((\d+),(\d+)\)""")

    private fun mulMatches(program: String): List<Pair<String, String>> = mulPattern.findAll(program)
        .map { matchResult ->
            val firstInt = matchResult.groupValues[1]
            val secondInt = matchResult.groupValues[2]
            firstInt to secondInt
        }.toList()

    fun sumOfMuls(): Int = sumOfMuls(normalisedProgram)

    private fun sumOfMuls(program: String): Int = mulMatches(program)
        .map { Pair(it.first.toInt(), it.second.toInt()) }
        .sumOf { it.first * it.second }

    fun sumOfDoMuls(): Int {
        return sumOfMuls(normalisedProgram
            .split("do()")
            .joinToString("") { it.substringBefore("don't()") }
        )
    }
}





