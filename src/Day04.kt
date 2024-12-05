fun main() {
    val directions = listOf(
        Pair(0, 1),  // up
        Pair(0, -1), // down
        Pair(-1, 0), // left
        Pair(1, 0),  // right
        Pair(-1, 1), // up-left
        Pair(1, 1),  // up-right
        Pair(-1, -1),// down-left
        Pair(1, -1)  // down-right
    )

    fun part1(input: List<String>): Int {
        var sum = 0

        for (y in input.indices) {
            for (x in input[y].indices) {
                if (input[y][x] != 'X') continue

                directions@
                for ((dx, dy) in directions) {
                    val charsLeft = mutableListOf('S', 'A', 'M')
                    var xx = x
                    var yy = y
                    while (charsLeft.isNotEmpty()) {
                        xx += dx
                        yy += dy
                        val char = charsLeft.removeLast()
                        if (char != input.getOrNull(yy)?.getOrNull(xx)) continue@directions
                    }
                    sum++
                }
            }
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        for (y in input.indices) {
            for (x in input[y].indices) {
                if (input[y][x] != 'A') continue

                val topLeft = input.getOrNull(y - 1)?.getOrNull(x - 1)
                val topRight = input.getOrNull(y - 1)?.getOrNull(x + 1)
                val bottomLeft = input.getOrNull(y + 1)?.getOrNull(x - 1)
                val bottomRight = input.getOrNull(y + 1)?.getOrNull(x + 1)

                if (!(topLeft == 'M' && bottomRight == 'S' || topLeft == 'S' && bottomRight == 'M')) continue
                if (!(topRight == 'M' && bottomLeft == 'S' || topRight == 'S' && bottomLeft == 'M')) continue

                sum++
            }
        }

        return sum
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
