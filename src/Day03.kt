fun main() {
    fun part1(input: List<String>): Int {
        return input.flatMap { """mul\((\d+),(\d+)\)""".toRegex().findAll(it) }
            .sumOf {
                val (a, b) = it.destructured
                a.toInt() * b.toInt()
            }
    }

    fun part2(input: List<String>): Int {
        var go = true
        return input.flatMap { """mul\((\d+),(\d+)\)|do(n't)?\(\)""".toRegex().findAll(it) }
            .sumOf {
                if (it.value.startsWith("mul") && go) {
                    val (a, b) = it.destructured
                    a.toInt() * b.toInt()
                } else if (it.value == "do()") {
                    go = true
                    "0".toInt()
                } else if (it.value == "don't()") {
                    go = false
                    "0".toInt()
                } else {
                    "0".toInt()
                }
            }
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)
    println(part2(testInput))
    check(part2(testInput) == 48)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}