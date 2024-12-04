package solutions

fun main() {

    Day03().test(161, 48)?.solve()
}

class Day03 : Solution() {
    override val day: Int = 3
    override fun part1(input: List<String>): Int = MulProgram(input).sumOfMuls()

    override fun part2(input: List<String>): Int = MulProgram(input).sumOfDoMuls()
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





