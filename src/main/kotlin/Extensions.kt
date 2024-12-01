fun String.tokenised(): List<String> = split("\\s+".toRegex())

fun String.tokenisedToInt(): List<Int> = tokenised().map { it.toInt() }

fun List<String>.tokenised(): List<List<String>> = map { it.tokenised() }

fun List<String>.tokenisedToInt(): List<List<Int>> = map { it.tokenisedToInt() }

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