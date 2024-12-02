import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/main/kotlin/input/$name.txt").readLines()

fun inputFileName(day: Int): String = "%02d".format(day)
fun testFileName(day: Int): String = "%02d".format(day) + "_test"

fun test(day: Int,
         part1: (lines: List<String>) -> Int,
         expected1: Int,
         part2: (lines: List<String>) -> Int,
         expected2: Int) {
    starline()
    println ("\nTest 1: " + part1(readInput(testFileName(day))))
    println ("Test 2: " + part2(readInput(testFileName(day))))
    check(part1(readInput(testFileName(day))) == expected1)
    check(part2(readInput(testFileName(day))) == expected2)
}

fun starline() {
    repeat(20) {
        print("*")
    }
}

fun solve(day: Int,
          part1: (lines: List<String>) -> Int,
          part2: (lines: List<String>) -> Int) {
    val input = readInput(inputFileName(day))
    starline()
    println("\nPart 1: " + part1(input))
    println("Part 2: " + part2(input))
    starline()
}


val numeronyms = mapOf(
    "one" to '1',
    "two" to '2',
    "three" to '3',
    "four" to '4',
    "five" to '5',
    "six" to '6',
    "seven" to '7',
    "eight" to '8',
    "nine" to '9'
)
