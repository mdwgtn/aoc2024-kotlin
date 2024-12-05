fun String.tokenised(): List<String> = trim().split("\\s+".toRegex())

fun String.tokenised(c: Char): List<String> = trim().split(c)

fun String.tokenisedToInt(): List<Int> = trim().tokenised().map { it.toInt() }
fun String.tokenisedToInt(c: Char): List<Int> = trim().tokenised(c).map { it.toInt() }

fun List<String>.tokenised(): List<List<String>> = map { it.trim().tokenised() }

fun List<String>.tokenised(c: Char): List<List<String>> = map { it.trim().tokenised(c) }

fun List<String>.tokenisedToInt(): List<List<Int>> = map { it.trim().tokenisedToInt() }

fun List<String>.tokenisedToInt(c: Char): List<List<Int>> = map { it.trim().tokenisedToInt(c)}

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

fun List<String>.splitByEmptyString(): List<List<String>> {
    return fold(mutableListOf<MutableList<String>>()) { acc, str ->
        if (str.isEmpty()) {
            if (acc.isEmpty() || acc.last().isNotEmpty()) acc.add(mutableListOf())
        } else {
            if (acc.isEmpty() || acc.last().isEmpty()) acc.add(mutableListOf())
            acc.last().add(str)
        }
        acc
    }.filter { it.isNotEmpty() }
}

fun List<String>.padded2d(padding: Char): List<String> {
    val padded = stream().map { "" + padding + it + padding}.toList().toMutableList()
    padded.addFirst(("" + padding).repeat(padded[0].length))
    padded.addLast( ("" + padding).repeat(padded[0].length))
    return padded
}

