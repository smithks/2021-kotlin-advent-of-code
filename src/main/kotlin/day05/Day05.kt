package day01.day05

import day01.readInput
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {

    val input = readInput("day05/Day05Input")

//    val result = Day05.part01(input)
//    println("Cells where 2 lines overlap: $result")

    val result = Day05.part02(input)
    println(result)

}

object Day05 {

    fun part01Full(): Int {
        val input = readInput("day05/Day05Input")

        return part01(input)
    }

    // Consider only horizontal and vertical lines. At how many points do at least two lines overlap?
    fun part01(input: List<String>): Int {

        val lines = input.getLines().filter {
            it.isVertical() || it.isHorizontal()
        }

        val graph = buildEmptyGraph(lines)

        chartLinesToGraph(graph, lines)

        return countGraphOverlap(graph)

    }

    fun part02(input: List<String>): Int {
        val lines = input.getLines().filter {
            it.isVertical() || it.isHorizontal() || it.is45Degree()
        }

        val graph = buildEmptyGraph(lines)

        chartLinesToGraph(graph, lines)

        return countGraphOverlap(graph)
    }

    fun buildEmptyGraph(lines: List<Line>): Array<Array<Int>> {
        // Find array dimensions
        val maxX = lines.findMaxX() + 1
        val maxY = lines.findMaxY() + 1

        // Create empty 2d array
        return Array(maxY) { Array(maxX) { 0 } }
    }

    // Charts the given lines into the graph provided
    private fun chartLinesToGraph(graph: Array<Array<Int>>, lines: List<Line>) {
        for (line in lines) {
            val start = line.start
            val end = line.end

            // (5,9) -> (9,9) horizontal line, y stays the same
            if (line.isHorizontal()) {
                for (x in min(start.x, end.x)..max(start.x, end.x)) {
                    graph[start.y][x]++
                }
            }

            // (5,9) -> (5, 5) vertical line, x stays the same
            if (line.isVertical()) {
                for (y in min(start.y, end.y)..max(start.y, end.y)) {
                    graph[y][start.x]++
                }
            }

            // (0,0) -> (2,2) 45 degree line
            if (line.is45Degree()) {
                chart45DegreeLine(graph, line)
            }

            val xDiff = abs(start.x - end.x)
            val yDiff = abs(start.y - end.y)
            // line of length 1 (9,9) -> (9,9)
            if (xDiff == 0 && yDiff == 0) {
                graph[start.y][start.x]++
            }
        }
    }

    fun chart45DegreeLine(graph: Array<Array<Int>>, line: Line) {
        // 1. find starting point (the point that is "above" the other on the graph)
        //      We will either travel:
        //      down and to the right, (0, 0) -> (2, 2)
        //      down and to the left, (2, 0) -> (0, 2)
        val (startingPoint, endingPoint) = if (line.start.y < line.end.y) {
            line.start to line.end
        } else {
            line.end to line.start
        }

        val numPoints = abs(startingPoint.x - endingPoint.x)
        if (endingPoint.x > startingPoint.x) {
            // down and to the right. Increment x and y by 1
            for (i in 0..numPoints) {
                graph[startingPoint.y + i][startingPoint.x + i]++
            }
        } else {
            // down and to the left. Decrement x by 1 Increment y by 1
            for (i in 0..numPoints) {
                graph[startingPoint.y + i][startingPoint.x - i]++
            }
        }
    }

    private fun countGraphOverlap(graph: Array<Array<Int>>, target: Int = 2): Int {
        var count = 0
        graph.forEach { row ->
            row.forEach { cell ->
                if (cell >= target) count++
            }
        }
        return count
    }

}

data class Point(val x: Int, val y: Int)

data class Line(val start: Point, val end: Point)

fun Line.isVertical(): Boolean = start.x == end.x

fun Line.isHorizontal(): Boolean = start.y == end.y

fun Line.is45Degree(): Boolean {
    val diffY = abs(start.y - end.y)
    val diffX = abs(start.x - end.x)
    // > 0 to avoid single point lines
    return diffY == diffX && diffY > 0
}

fun List<String>.getLines(): List<Line> = map { line ->
    val points = line.split("->").filter { it.isNotBlank() }
    Line(points.first().getPoint(), points.last().getPoint())
}

// Assumes input in the format "32,34"
private fun String.getPoint(): Point {
    val (x, y) = split(",").map { it.trim().toInt() }
    return Point(x, y)
}

private fun List<Line>.findMaxX(): Int =
    maxOf(maxByOrNull { it.start.x }?.start?.x ?: -1, maxByOrNull { it.end.x }?.end?.x ?: -1
    )

private fun List<Line>.findMaxY(): Int =
    maxOf(maxByOrNull { it.start.y }?.start?.y ?: -1, maxByOrNull { it.end.y }?.end?.y ?: -1
    )