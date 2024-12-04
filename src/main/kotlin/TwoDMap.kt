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

    val height: Int = input.size
    val width: Int = input[0].length
    val coordinates = allCoordinates()

    fun at(pos: Coordinates): Char = input[pos.y][pos.x]

    fun neighbour(pos: Coordinates, direction: Int = 5): Coordinates? {
        val n = pos.neighbour(direction)
        return if (locationExists(n)) n else null
    }

    fun isOnAnEdge(pos: Coordinates): Boolean = (pos.x == 0 || pos.x == width - 1 || pos.y == 0 || pos.y == height - 1)

    private fun locationExists(pos: Coordinates?): Boolean = pos != null && pos.x in 0..<width && pos.y in 0..<height

    private fun allCoordinates(): List<Coordinates> {
        val result = mutableListOf<Coordinates>()
        for (x in range(0, width)) {
            for (y in range(0, height)) {
                result.add(Coordinates(x, y))
            }
        }
        return result;
    }
}