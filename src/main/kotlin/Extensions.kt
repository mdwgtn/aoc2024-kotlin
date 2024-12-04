fun String.tokenised(): List<String> = trim().split("\\s+".toRegex())

fun String.tokenisedToInt(): List<Int> = trim().tokenised().map { it.toInt() }

fun List<String>.tokenised(): List<List<String>> = map { it.trim().tokenised() }

fun List<String>.tokenisedToInt(): List<List<Int>> = map { it.trim().tokenisedToInt() }

inline fun <reified T> List<List<T>>.transposed(): List<List<T>> =
    List(this[0].size) { index -> this.map { it[index] } }

fun String.numeronymToInt(): Int? =
    when (this.lowercase()) {
        "one" -> 1
        "two" -> 2
        "three" -> 3
        "four" -> 4
        "five" -> 5
        "six" -> 6
        "seven" -> 7
        "eight" -> 8
        "nine" -> 9
        else -> null
    }

fun List<String>.padded2d(padding: Char): List<String> {
    val padded = stream().map { "" + padding + it + padding}.toList().toMutableList()
    padded.addFirst(("" + padding).repeat(padded[0].length))
    padded.addLast( ("" + padding).repeat(padded[0].length))
    return padded
}

