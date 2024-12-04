import java.util.stream.IntStream.range

class Coordinates(val x: Int, val y: Int) {
    fun neighbour(direction: Int): Coordinates =
        when (direction) {
            1 -> Coordinates(x - 1, y + 1)
            2 -> Coordinates(x, y + 1)
            3 -> Coordinates(x + 1, y + 1)
            4 -> Coordinates(x - 1, y)
            5 -> Coordinates(x, y)
            6 -> Coordinates(x + 1, y)
            7 -> Coordinates(x - 1, y - 1)
            8 -> Coordinates(x, y - 1)
            9 -> Coordinates(x + 1, y - 1)
            else -> throw IllegalArgumentException()
        }
}


class TwoDMap(private val input: List<String>) {

    private val height: Int = input.size
    private val width: Int = input[0].length
    private val coordinates = allCoordinates()

    private fun at(pos: Coordinates): Char = input[pos.y][pos.x]

    private fun neighbour(pos: Coordinates, direction: Int = 5): Coordinates? {
        val n = pos.neighbour(direction)
        return if (locationExists(n)) n else null
    }

    private fun allCoordinates(): List<Coordinates> {
        val result = mutableListOf<Coordinates>()
        for (x in range(0, width)) {
            for (y in range(0, height)) {
                result.add(Coordinates(x, y))
            }
        }
        return result;
    }

    private fun locationExists(pos: Coordinates?): Boolean = pos != null && pos.x in 0..<width && pos.y in 0..<height


    private fun hasDirectionalLinearSequenceFrom(sequence: String, pos: Coordinates, direction: Int): Boolean {
        if (sequence.isEmpty()) return true
        if (at(pos) == sequence[0]) {
            if (sequence.length == 1) {
                return true
            }
            val n = neighbour(pos, direction)
            return if (n == null) false else hasDirectionalLinearSequenceFrom(sequence.substring(1), n, direction)
        }
        return false
    }

    private fun countOfLinearSequencesFrom(sequence: String, pos: Coordinates): Int {
        return listOf(1, 2, 3, 4, 6, 7, 8, 9).count {
            hasDirectionalLinearSequenceFrom(sequence, pos, it)
        }
    }

    fun countOfLinearSequences(sequence: String): Int =
        coordinates.sumOf { countOfLinearSequencesFrom(sequence, it) }

    private fun hasXWordCentredAt(pos: Coordinates, centreLetter: Char, firstLetter: Char, lastLetter: Char): Boolean {
        val edgeLetters = listOf(firstLetter, lastLetter)
        if (at(pos) != centreLetter) {
            return false
        }
        val topLeft = neighbour(pos, 7)
        val topRight = neighbour(pos, 9)
        val bottomLeft = neighbour(pos, 1)
        val bottomRight = neighbour(pos, 3)
        if (!listOf(topLeft, topRight, bottomLeft, bottomRight).all { locationExists(it) }) {
            return false
        }
        val topLeftLetter = at(topLeft!!)
        val topRightLetter = at(topRight!!)
        val bottomLeftLetter = at(bottomLeft!!)
        val bottomRightLetter = at(bottomRight!!)
        val actualEdgeLetters = listOf(topLeftLetter, topRightLetter, bottomLeftLetter, bottomRightLetter)

        // the edge letters must appear twice each
        if (!(edgeLetters.all { 2 == actualEdgeLetters.count { actual -> it == actual } })) {
            return false
        }
        // filter out SAS and MAM, otherwise this is a match
        return !(topLeftLetter == bottomRightLetter || topRightLetter == bottomLeftLetter);
    }

    fun countOfXWords(sequence: String): Int =
         coordinates.count { hasXWordCentredAt(it, sequence[1], sequence[0], sequence[2])  }
}