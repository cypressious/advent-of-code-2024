fun main() {
    val regex = """mul\((\d+),(\d+)\)""".toRegex()

    fun mul(result: MatchResult): Int {
        return result.groups[1]!!.value.toInt() * result.groups[2]!!.value.toInt()
    }

    fun part1(input: List<String>): Int {
        return regex
            .findAll(input.joinToString(""))
            .sumOf { mul(it) }
    }

    val regex2 = """mul\((\d+),(\d+)\)|do\(\)|don't\(\)""".toRegex()

    fun part2(input: List<String>): Int {
        var enabled = true
        var sum = 0
        for (it in regex2.findAll(input.joinToString(""))) {
            if (it.value.startsWith("don")) {
                enabled = false
            } else if (it.value.startsWith("do")) {
                enabled = true
            } else if (enabled){
                sum += mul(it)
            }
        }
        return sum
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)
    check(part2(listOf("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")) == 48)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
