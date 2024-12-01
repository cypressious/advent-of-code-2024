import kotlin.math.abs

fun main() {
    fun parse(input: List<String>): Pair<MutableList<Int>, MutableList<Int>> {
        val l1 = mutableListOf<Int>()
        val l2 = mutableListOf<Int>()

        for (string in input) {
            val (x, y) = string.split("\\s+".toRegex())
            l1.add(x.toInt())
            l2.add(y.toInt())
        }
        return Pair(l1, l2)
    }

    fun part1(input: List<String>): Int {
        val (l1, l2) = parse(input)

        l1.sort()
        l2.sort()

        return l1.zip(l2).sumOf { (x,y) -> abs(x-y) }
    }

    fun part2(input: List<String>): Int {
        val (l1, l2) = parse(input)
        val counts = l2.groupingBy { it }.eachCount()

        return l1.sumOf { it * counts.getOrElse(it) { 0 } }
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
