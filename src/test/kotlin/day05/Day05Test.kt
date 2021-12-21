package day05

import day01.day05.*
import day01.print2DArray
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day05Test {

    val input = listOf(
        "0,9 -> 5,9",
        "8,0 -> 0,8",
        "9,4 -> 3,4",
        "2,2 -> 2,1",
        "7,0 -> 7,4",
        "6,4 -> 2,0",
        "0,9 -> 2,9",
        "3,4 -> 1,4",
        "0,0 -> 8,8",
        "5,5 -> 8,2",
    )

    @Test
    fun `test point parsing`() {
        val lines = input.getLines()

        assertEquals(Point(0, 9), lines.first().start)
        assertEquals(Point(5, 9), lines.first().end)
        assertEquals(Point(5, 5), lines.last().start)
        assertEquals(Point(8, 2), lines.last().end)
    }

    @Test
    fun `test part 01 sample`() {
        val result = Day05.part01(input)
        assertEquals(5, result)
    }

    @Test
    fun `test full part01`() {
        val result = Day05.part01Full()

        assertEquals(4993, result)
    }

    @Test
    fun `test is 45 degree`() {
        val start = Point(2, 0)
        val end = Point(3, 1)
        val line = Line(start, end)

        Assertions.assertTrue(line.is45Degree())
        Assertions.assertFalse(Line(Point(1, 1), Point(1, 1)).is45Degree())
    }

    @Test
    fun `test charting 45 degree`() {
        val start = Point(2, 0)
        val end = Point(3, 1)

        val lines = listOf(Line(start, end))

        val graph = Day05.buildEmptyGraph(lines)
        Day05.chart45DegreeLine(graph, Line(start, end))

        graph.print2DArray()
        assertEquals(1, graph[0][2])
        assertEquals(1, graph[1][3])
    }

    @Test
    fun `test part2 with sample`() {
        val result = Day05.part02(input)
        assertEquals(12, result)
    }
}