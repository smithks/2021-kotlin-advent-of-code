package day02

import day01.day02.Day02
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day02Test {

    val testInput = listOf(
        "forward 5",
        "down 5",
        "forward 8",
        "up 3",
        "down 8",
        "forward 2"
    )

    @Test
    fun part01Test() {
        Assertions.assertEquals(150, Day02.part01CalculateProduct(testInput))
    }

    @Test
    fun part02Test() {
        Assertions.assertEquals(900, Day02.part02CalculateProduct(testInput))
    }
}