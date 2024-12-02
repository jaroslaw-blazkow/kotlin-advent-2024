import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        return input.map { it.split("\\s+".toRegex()).map(String::toInt) }
            .filter { isSafe(it) }.size
    }

    fun part2(input: List<String>): Int {
        return input.map { it.split("\\s+".toRegex()).map(String::toInt) }
            .filter { isSafeWithOneRemoval(it) }.size
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

private fun isSafeWithOneRemoval(report: List<Int>): Boolean {
    return if (isSafe(report)) true
    else {
        report.indices.any { index ->
            val modifiedList = report.filterIndexed { i, _ -> i != index }
            isSafe(modifiedList)
        }
    }
}

private fun isSafe(report: List<Int>): Boolean {
    return report.windowed(2).all { (a, b) -> abs(a - b) in 1..3 } &&
            (report.windowed(2).all { (a, b) -> a > b } ||
                    report.windowed(2).all { (a, b) -> a < b })
}
