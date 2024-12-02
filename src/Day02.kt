import kotlin.math.abs

fun main() {
    fun check(increasing: Boolean, a: Int, b: Int): Boolean {
        return (if (increasing) b > a else a > b) && abs(b - a) in 1..3
    }

    fun check(numbers: List<Int>): Boolean {
        val increasing = numbers[1] > numbers[0]
        return numbers.zipWithNext().all { (a, b) ->
            check(increasing, a, b)
        }
    }

    fun part1(input: List<String>): Int {
        return input.count { line ->
            val numbers = line.split(" ").map { it.toInt() }
            check(numbers)
        }
    }

    fun part2(input: List<String>): Int {
        return input.count { line ->
            val numbers = line.split(" ").map { it.toInt() }
            check(numbers) || numbers.indices.any { j ->
                val filteredNumbers = numbers.filterIndexed { i, _ -> i != j }
                check(filteredNumbers)
            }
        }
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
