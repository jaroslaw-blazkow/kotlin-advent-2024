fun main() {
    fun part1(input: List<String>): Int {
        val x = input.map { it.toCharArray() }
        var result = 0
        for (i in x.indices) {
            for (j in x[i].indices) {
                if (checkHorizontal(x, i, j)) result++
                if (checkHorizontalBackward(x, i, j)) result++
                if (checkVertical(x, i, j)) result++
                if (checkVerticalBackward(x, i, j)) result++
                if (checkDiagonalRight(x, i, j)) result++
                if (checkDiagonalLeft(x, i, j)) result++
                if (checkDiagonalRightBackward(x, i, j)) result++
                if (checkDiagonalLeftBackward(x, i, j)) result++
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val x = input.map { it.toCharArray() }
        var result = 0
        for (i in x.indices) {
            for (j in x[i].indices) {
                if (i == 0 || j == 0 || i == x.size - 1 || j == x[i].size - 1) continue
                    if (x[i][j] == 'A') {
                        if ((x[i-1][j-1] == 'M' && x[i+1][j+1] == 'S') || (x[i-1][j-1] == 'S' && x[i+1][j+1] == 'M')) {
                            if ((x[i+1][j-1] == 'M' && x[i-1][j+1] == 'S') || (x[i+1][j-1] == 'S' && x[i-1][j+1] == 'M'))
                                result++
                        }
                    }
            }
        }
        return result
    }

    val testInput = readInput("Day04_test")
    println(part1(testInput))
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}

private fun checkHorizontal(x: List<CharArray>, i: Int, j: Int): Boolean {
    return j + 3 < x[i].size && x[i][j] == 'X' && x[i][j + 1] == 'M' && x[i][j + 2] == 'A' && x[i][j + 3] == 'S'
}

private fun checkHorizontalBackward(x: List<CharArray>, i: Int, j: Int): Boolean {
    return j - 3 >= 0 && x[i][j] == 'X' && x[i][j - 1] == 'M' && x[i][j - 2] == 'A' && x[i][j - 3] == 'S'
}

private fun checkVertical(x: List<CharArray>, i: Int, j: Int): Boolean {
    return i + 3 < x.size && x[i][j] == 'X' && x[i + 1][j] == 'M' && x[i + 2][j] == 'A' && x[i + 3][j] == 'S'
}

private fun checkVerticalBackward(x: List<CharArray>, i: Int, j: Int): Boolean {
    return i - 3 >= 0 && x[i][j] == 'X' && x[i - 1][j] == 'M' && x[i - 2][j] == 'A' && x[i - 3][j] == 'S'
}

private fun checkDiagonalRight(x: List<CharArray>, i: Int, j: Int): Boolean {
    return i + 3 < x.size && j + 3 < x[i].size && x[i][j] == 'X' && x[i + 1][j + 1] == 'M' && x[i + 2][j + 2] == 'A' && x[i + 3][j + 3] == 'S'
}

private fun checkDiagonalLeft(x: List<CharArray>, i: Int, j: Int): Boolean {
    return i + 3 < x.size && j - 3 >= 0 && x[i][j] == 'X' && x[i + 1][j - 1] == 'M' && x[i + 2][j - 2] == 'A' && x[i + 3][j - 3] == 'S'
}

private fun checkDiagonalRightBackward(x: List<CharArray>, i: Int, j: Int): Boolean {
    return j + 3 < x[i].size && i - 3 >= 0 && x[i][j] == 'X' && x[i - 1][j + 1] == 'M' && x[i - 2][j + 2] == 'A' && x[i - 3][j + 3] == 'S'
}

private fun checkDiagonalLeftBackward(x: List<CharArray>, i: Int, j: Int): Boolean {
    return i - 3 >= 0 && j - 3 >= 0 && x[i][j] == 'X' && x[i - 1][j - 1] == 'M' && x[i - 2][j - 2] == 'A' && x[i - 3][j - 3] == 'S'
}