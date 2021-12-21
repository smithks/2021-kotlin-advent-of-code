package day01

fun main() {
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day01/Day01Input")
    print(Day01.part1countLarger(testInput))
//    print(Day01.part2CountLarger(testInput))
}

internal object Day01 {
    // Find the number of entries that are larger than the previous entry
    fun part1countLarger(input: List<String>): Int {
        return input.map {
            it.toInt()
        }.foldIndexed(0) { index, acc, i ->
            if (index == 0) {
                return@foldIndexed acc
            }

            return@foldIndexed if (i > input[index - 1].toInt()) {
                acc + 1
            } else {
                acc
            }
        }
    }

    // Consider sums of a three-measurement sliding window. How many sums are larger than the previous sum?
    fun part2CountLarger(input: List<String>): Int {
        val nums = input.map {
            it.toInt()
        }
        val summedThrees = nums.mapIndexed { index, entry: Int ->
            var sum = entry

            // Handle last item
            if (index < nums.lastIndex) {
                sum += nums[index+1]
            }

            // Handle second to last item
            if (index < nums.lastIndex-1) {
                sum += nums[index+2]
            }

            sum
        }.map {
            it.toString()
        }

        return part1countLarger(summedThrees)
    }
}
