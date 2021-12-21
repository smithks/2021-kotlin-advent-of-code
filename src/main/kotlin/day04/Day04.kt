package day01.day04

import day01.readInput

fun main() {
    val input = readInput("day04/Day04Input")
//    println(Day04.part1(input))
    println(Day04.part2(input))
}

object Day04 {

    fun part1(input: List<String>): Int {
        val drawnNumbers = input[0].split(",").map { it.toInt() }

        val boards = getBoards(input.subList(1, input.size))

        drawnNumbers.forEach { number ->
            boards.forEach { board ->
                board.markNumber(number)
                if (board.checkBingo()) {
                    return board.unmarkedSum() * number
                }
            }
        }

        return -1
    }

    fun part2(input: List<String>): Int {
        val drawnNumbers = input[0].split(",").map { it.toInt() }

        val boards: MutableList<GameBoard?> = getBoards(input.subList(1, input.size)).toMutableList()

        drawnNumbers.forEach { number ->
            boards.forEachIndexed { index, board ->
                board?.markNumber(number)
                if (board?.checkBingo() == true) {
                    if (boards.count { it != null } == 1) {
                        // If this was the last board report result
                        return board.unmarkedSum() * number
                    } else {
                        // Remove this board if it wasn't the last
                        boards[index] = null
                    }
                }
            }
        }

        return -1
    }

    fun getBoards(input: List<String>): List<GameBoard> {
        val boards = mutableListOf<GameBoard>()
        // Find first blank line in input
        val nextGroupStart = input.indexOfFirst { it.isBlank() }
        if (nextGroupStart == -1)
            return boards

        var reducedList = input.subList(nextGroupStart + 1, input.size)
        while (reducedList.isNotEmpty()) {
            // Find end of this group
            var endIndex = reducedList.indexOfFirst { it.isBlank() }
            if (endIndex == -1)
                endIndex = reducedList.size
            // Add new board
            boards.add(GameBoard(reducedList.subList(0, endIndex)))
            // break if this was the last entry
            if (endIndex == reducedList.size)
                break
            reducedList = reducedList.subList(endIndex + 1, reducedList.size)
        }
        return boards
    }

    class GameBoard(rows: List<String>) {

        val board: Array<Array<Cell>> = Array(rows.size) {
            Array(rows.size) { Cell(-1) }
        }

        init {
            rows.forEachIndexed { rowIndex, row ->
                row.parseInts().forEachIndexed { columnIndex, cellValue ->
                    board[rowIndex][columnIndex] = Cell(cellValue)
                }
            }
        }

        // Check if number is on board
        fun markNumber(number: Int) {
            board.forEach { row ->
                row.forEach { cell ->
                    if (cell.value == number)
                        cell.marked = true
                }
            }
        }

        fun checkBingo(): Boolean {
            val rowBingo = board.any { row ->
                row.all { it.marked }
            }
            if (rowBingo)
                return true

            for (j in board.indices) {
                var columnBingo = true
                for (i in board.indices) {
                    columnBingo = columnBingo && board[i][j].marked
                }
                if (columnBingo)
                    return true
            }

            return false
        }

        fun unmarkedSum(): Int {
            return board.fold(0) { rowAcc: Int, row: Array<Cell> ->
                rowAcc + row.fold(0) { colAcc: Int, cell: Cell ->
                    if (cell.marked.not()) {
                        colAcc + cell.value
                    } else {
                        colAcc
                    }
                }
            }
        }

        fun print() {
            board.forEach {
                it.forEach {
                    if (it.marked) {
                        print("[${it.value}] ")
                    } else {
                        print("${it.value} ")
                    }
                }
                println()
            }
        }

        data class Cell(val value: Int, var marked: Boolean = false)
    }

    fun String.parseInts(): List<Int> = split(" ")
        .filter { it.isNotBlank() }
        .map { it.toInt() }
}

