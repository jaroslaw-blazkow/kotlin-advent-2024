import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        var (first, second) = input
                .map {
                    val (first, second) = it.split("\\s+".toRegex()).map(String::toInt)
                    first to second
                }
                .let { pairs -> pairs.map { it.first } to pairs.map { it.second } }
        first = first.sorted()
        second = second.sorted()
        return first.mapIndexed { index, i -> abs(second[index].minus(i)) }.sum()
    }

    fun part2(input: List<String>): Int {
        val (first, second) = input
                .map {
                    val (first, second) = it.split("\\s+".toRegex()).map(String::toInt)
                    first to second
                }
                .let { pairs -> pairs.map { it.first } to pairs.map { it.second } }
        return first.sumOf { f -> second.count { it == f } * f }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
