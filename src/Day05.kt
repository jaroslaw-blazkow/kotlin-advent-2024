fun main() {
    fun part1(input: List<String>): Int {
        val (rules, updates) = input.filter { it.isNotBlank() }.partition { it.contains("|") }
        val l = mutableListOf<Int>()
        val r = mutableListOf<Int>()
        rules.forEach {
            val (a, b) = it.split("|").map { num -> num.toInt() }
            l.add(a)
            r.add(b)
        }
        return updates.sumOf { update ->
            val order = update.split(",").map(String::toInt)
            if (match(order, l, r)) {
                order[(order.size - 1) / 2]
            } else {
                0
            }
        }
    }

    fun part2(input: List<String>): Int {
        val (rules, updates) = input.filter { it.isNotBlank() }.partition { it.contains("|") }
        val l = mutableListOf<Int>()
        val r = mutableListOf<Int>()
        rules.forEach {
            val (a, b) = it.split("|").map { num -> num.toInt() }
            l.add(a)
            r.add(b)
        }
        return updates.sumOf { update ->
            val order = update.split(",").map(String::toInt)
            val match = match(order, l, r)
            if (match) {
                0
            } else {
                var orderMutable = order.toMutableList()
                do {
                    orderMutable = reorder(orderMutable, l, r)
                } while (!match(orderMutable, l, r))
                println(orderMutable)
                orderMutable[(orderMutable.size - 1) / 2]
            }
        }
    }

    val testInput = readInput("Day05_test")
    println(part1(testInput))
    check(part1(testInput) == 143)
    println(part2(testInput))

    check(part2(testInput) == 123)

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}

private fun reorder(order: List<Int>, l: List<Int>, r: List<Int>): MutableList<Int> {
    val reordered = order.toMutableList()

    order.mapIndexed { elementIdx, orderElement ->
        val ruleIndex =
            l.mapIndexed { index, i -> if (i == orderElement && order.contains(r[index])) index else null }
                .filterNotNull()
        val first = ruleIndex.firstOrNull {
            elementIdx > order.indexOf(r[it])
        }
        if (first != null) {
            val x = order.indexOf(r[first])
            reordered[elementIdx] = r[first]
            reordered[x] = l[first]
        }

        val ruleIndexR =
            r.mapIndexed { index, i -> if (i == orderElement && order.contains(l[index])) index else null }
                .filterNotNull()
        val firstR = ruleIndexR.firstOrNull {
            elementIdx < order.indexOf(l[it])
        }
        if (firstR != null) {
            val x = order.indexOf(l[firstR])
            reordered[elementIdx] = r[firstR]
            reordered[x] = l[firstR]
        }
    }
    return reordered
}

private fun match(order: List<Int>, l: List<Int>, r: List<Int>): Boolean {
    return order.all { orderElement ->
        val ruleIndex =
            l.mapIndexed { index, i -> if (i == orderElement && order.contains(r[index])) index else null }
                .filterNotNull()
        ruleIndex.all {
            order.lastIndexOf(l[it]) < order.indexOf(r[it])
        }
    }
}