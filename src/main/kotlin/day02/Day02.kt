package day01.day02

import day01.readInput

fun main() {

    val input = readInput("day02/Day02Input")

//    println(Day02.part01CalculateProduct(input))
    println(Day02.part02CalculateProduct(input))
}

internal object Day02 {

    /**
     * Calculate the horizontal position and depth you would
     * have after following the planned course. What do you
     * get if you multiply your final horizontal position by your final depth?
     */
    fun part01CalculateProduct(directions: List<String>): Int {
        var horizontal = 0
        var depth = 0
        directions
            .toVectors()
            .forEach {
                when (it.direction) {
                    DIRECTION.FORWARD -> horizontal += it.value
                    DIRECTION.UP -> depth -= it.value
                    DIRECTION.DOWN -> depth += it.value
                }
            }

        return horizontal * depth
    }

    fun part02CalculateProduct(directions: List<String>): Int {
        var horizontal = 0
        var depth = 0
        var aim = 0
        directions
            .toVectors()
            .forEach {
                when (it.direction) {
                    DIRECTION.FORWARD -> {
                        horizontal += it.value
                        depth += (aim * it.value)
                    }
                    DIRECTION.UP -> aim -= it.value
                    DIRECTION.DOWN -> aim += it.value
                }
            }

        return horizontal * depth
    }

    // Parse vectors from our input of strings
    private fun List<String>.toVectors(): List<Vector> = map { entry ->
        val direction: DIRECTION = DIRECTION.valueOf(entry.split(" ").first().uppercase())
        val value: Int = entry.split(" ")[1].toInt()
        Vector(value, direction)
    }

    enum class DIRECTION {
        FORWARD, UP, DOWN
    }

    internal data class Vector(
        val value: Int,
        val direction: DIRECTION

    )
}

