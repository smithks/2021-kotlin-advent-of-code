package day01.day03

import day01.readInput

fun main() {

    val input = readInput("day03/Day03Input")

//    println(Day03.part01(input))
    println(Day03.part02(input))
}

internal object Day03 {

    /**
     * Use the binary numbers in your diagnostic report
     * to calculate the gamma rate and epsilon rate, then
     * multiply them together. What is the power consumption of the submarine?
     */
    fun part01(reports: List<String>): Int {
        val oneCounts = getOccurancesMap(reports)

        var gamma = ""
        var epsilon = ""
        for (index in 0 until reports.first().length) {
            val ones = oneCounts.getOrDefault(index, 0)
            val zeroes = reports.size - ones
            if (ones > zeroes) {
                gamma += "1"
                epsilon += "0"
            } else {
                gamma += "0"
                epsilon += "1"
            }
        }

        return gamma.toInt(2) * epsilon.toInt(2)
    }

    fun part02(reports: List<String>): Int {
        return findOxygenGeneratorRating(reports).toInt(2) * findC02ScrubberRating(reports).toInt(2)
    }

    fun findOxygenGeneratorRating(reports: List<String>, position: Int = 0): String {
        return if (reports.size == 1) {
            reports.first()
        } else {
            val oneCounts = getOccurancesMap(reports)
            val ones = oneCounts.getOrDefault(position, 0)
            val zeroes = reports.size - ones
            val filteredList = if (ones >= zeroes) {
                reports.filter { it[position] == '1' }
            } else {
                reports.filter { it[position] == '0' }
            }
            findOxygenGeneratorRating(filteredList, position + 1)
        }
    }


    fun findC02ScrubberRating(reports: List<String>, position: Int = 0): String {
        return if (reports.size == 1) {
            reports.first()
        } else {
            val oneCounts = getOccurancesMap(reports)
            val ones = oneCounts.getOrDefault(position, 0)
            val zeroes = reports.size - ones
            val filteredList = if (ones >= zeroes) {
                reports.filter { it[position] == '0' }
            } else {
                reports.filter { it[position] == '1' }
            }
            findC02ScrubberRating(filteredList, position + 1)
        }
    }

    fun getOccurancesMap(reports: List<String>): Map<Int, Int> {
        val oneCounts = mutableMapOf<Int, Int>()

        reports.forEach { report ->
            report.forEachIndexed { index, c ->
                if (c == '1') {
                    oneCounts[index] = oneCounts.getOrDefault(index, 0) + 1
                }
            }
        }

        return oneCounts
    }

}