package solutions

import splitByEmptyString
import tokenisedToInt


fun main() {
    Day05().test(143, 123)?.solve()
}

class Day05 : Solution() {
    override val day: Int = 5
    override fun part1(input: List<String>): Int = UpdateChecker(input).validUpdateSum
    override fun part2(input: List<String>): Int = UpdateChecker(input).forcedValidUpdateSum
}

class UpdateChecker(input: List<String>) {
    private val rules = input.splitByEmptyString()[0]
    private val updates = input.splitByEmptyString()[1].tokenisedToInt(',')
    private val predecessors = Predecessors(rules)
    val validUpdateSum = validMiddle()
    val forcedValidUpdateSum = forceAllValid()

    private fun validMiddle(): Int {
        return updates.sumOf { Update(it, predecessors).validMiddlePage() }
    }

    private fun forceAllValid(): Int {
        val invalidUpdates = updates.filter { Update(it, predecessors).validMiddlePage() == 0 }
        var sum = 0
        for (update in invalidUpdates) {
            var delta = 0
            var updatedUpdate = Update(update, predecessors)
            while (delta == 0) {
                updatedUpdate = updatedUpdate.withImprovedOrdering()
                delta = updatedUpdate.validMiddlePage()
            }
            sum += delta
        }
        return sum
    }
}

class Update(private val sequence: List<Int>, private val predecessors: Predecessors) {
    private fun pageIsValid(i: Int): Boolean =
        (0 until i).all { predecessors.hasPredecessor(sequence[i], sequence[it]) }

    private fun allPagesAreValid(): Boolean = sequence.indices.all { pageIsValid(it) }

    fun validMiddlePage(): Int = if (allPagesAreValid()) sequence[sequence.size / 2] else 0

    private fun firstInvalidPagePair(): Pair<Int, Int>? {
        val firstInvalid = sequence.indices.firstOrNull { !pageIsValid(it) } ?: return null

        val becauseItIsPrecededBy =
            (0 until firstInvalid).firstOrNull { !predecessors.hasPredecessor(sequence[firstInvalid], sequence[it]) }
                ?: return null

        return Pair(firstInvalid, becauseItIsPrecededBy)
    }

    private fun swapping(indicesToSwap: Pair<Int, Int>): Update {
        val temp = sequence[indicesToSwap.first]
        val newSequence = sequence.toMutableList()
        newSequence[indicesToSwap.first] = sequence[indicesToSwap.second]
        newSequence[indicesToSwap.second] = temp
        return Update(newSequence, predecessors)
    }

    fun withImprovedOrdering(): Update {
        val swapUs = firstInvalidPagePair() ?: return this
        return swapping(swapUs)
    }
}

class Predecessors(rules: List<String>) {
    private val normalisedRules = rules.tokenisedToInt('|')
    private val predecessors = predecessorTable()

    fun hasPredecessor(page: Int, candidate: Int): Boolean = predecessors[page].contains(candidate)

    private fun predecessorTable(): List<Set<Int>> {
        val result: List<MutableSet<Int>> = List(100) { mutableSetOf() }
        normalisedRules.forEach { result[it[1]].add(it[0]) }
        return result
    }
}





