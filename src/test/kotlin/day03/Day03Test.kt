package day03

import day01.day03.Day03
import day02.Day02Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day03Test {

    val input = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010"
    )

    @Test
    fun part01Test() {
        Assertions.assertEquals(198, Day03.part01(input))
    }

    @Test
    fun part02Test() {
        Assertions.assertEquals(230, Day03.part02(input))
    }

    @Test
    fun `oxygen generator test`() {
        Assertions.assertEquals("10111", Day03.findOxygenGeneratorRating(input))
    }

    @Test
    fun `C02 scrubber test`() {
        Assertions.assertEquals("01010", Day03.findC02ScrubberRating(input))
    }


}