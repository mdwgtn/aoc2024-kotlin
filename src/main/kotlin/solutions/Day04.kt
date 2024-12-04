package solutions

import Coordinates
import TwoDMap

fun main() {
    Day04().test(18, 9)?.solve()
}

class Day04 : Solution() {
    override val day: Int = 4
    override fun part1(input: List<String>): Int =
        XmasCount(input).countOfLinearSequences("XMAS")

    override fun part2(input: List<String>): Int =
        XmasCount(input).countOfXWords("SAM")
}

class XmasCount(input: List<String>) {
    private val map = TwoDMap(input)

    private fun hasDirectionalLinearSequenceFrom(sequence: String, pos: Coordinates, direction: Int): Boolean {
        if (sequence.isEmpty()) {
            return true
        }
        with(map) {
            if (at(pos) != sequence[0]) {
                return false
            }
            if (sequence.length == 1) {
                return true
            }
            val n = neighbour(pos, direction)
            return if (n == null) false else hasDirectionalLinearSequenceFrom(sequence.substring(1), n, direction)
        }
    }

    private fun countOfLinearSequencesFrom(sequence: String, pos: Coordinates): Int {
        return listOf(1, 2, 3, 4, 6, 7, 8, 9).count {
            hasDirectionalLinearSequenceFrom(sequence, pos, it)
        }
    }

    fun countOfLinearSequences(sequence: String): Int =
        map.coordinates.sumOf { countOfLinearSequencesFrom(sequence, it) }

    private fun hasXWordCentredAt(pos: Coordinates, centreLetter: Char, firstLetter: Char, lastLetter: Char): Boolean {
        val edgeLetters = listOf(firstLetter, lastLetter)
        with(map) {
            if (at(pos) != centreLetter || isOnAnEdge(pos)) {
                return false
            }

            val actualEdgeLetters = listOf(
                at(neighbour(pos, 7)!!),
                at(neighbour(pos, 9)!!),
                at(neighbour(pos, 1)!!),
                at(neighbour(pos, 3)!!)
            )

            // the edge letters must appear twice each
            if (!(edgeLetters.all { 2 == actualEdgeLetters.count { actual -> it == actual } })) {
                return false
            }
            // filter out SAS and MAM, otherwise this is a match
            return !(actualEdgeLetters[0] == actualEdgeLetters[3] || actualEdgeLetters[1] == actualEdgeLetters[2]);
        }
    }

    fun countOfXWords(sequence: String): Int =
        map.coordinates.count { hasXWordCentredAt(it, sequence[1], sequence[0], sequence[2]) }

}
