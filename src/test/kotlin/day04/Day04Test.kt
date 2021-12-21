package day04

import day01.day04.Day04
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.math.sin

internal class Day04Test {

    val input = listOf(
        "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1",
        "",
        "22 13 17 11  0",
        "8  2 23  4 24",
        "21  9 14 16  7",
        "6 10  3 18  5",
        "1 12 20 15 19",
        "",
        "3 15  0  2 22",
        "9 18 13 17  5",
        "19  8  7 25 23",
        "20 11 10 24  4",
        "14 21 16 12  6",
        "",
        "14 21 17 24  4",
        "10 16 15  9 19",
        "18  8 23 26 20",
        "22 11 13  6  5",
        "2  0 12  3  7",
    )

    val singleBoard = listOf(
        "14 21 17 24  4",
        "10 16 15  9 19",
        "18  8 23 26 20",
        "22 11 13  6  5",
        "2  0 12  3  7",
    )

    val smallBoard = listOf(
        "3 8",
        "5 10"
    )

    @Test
    fun `board test`() {
        val board = Day04.GameBoard(singleBoard)
        assertEquals(14, board.board[0][0].value)
        assertEquals(16, board.board[1][1].value)
        assertEquals(7, board.board[4][4].value)
    }

    @Test
    fun `get board test`() {
        val boards = Day04.getBoards(input.subList(1, input.size))
        assertEquals(3, boards.size)
        assertEquals(19, boards[0].board[4][4].value)
        assertEquals(7, boards[2].board[4][4].value)
    }

    @Test
    fun `check vertical bingo test`() {
        val board = Day04.GameBoard(singleBoard)

        assertEquals(false, board.checkBingo())

        board.markNumber(14)
        board.markNumber(10)
        board.markNumber(18)
        board.markNumber(22)
        board.markNumber(2)

        assertEquals(true, board.checkBingo())
    }

    @Test
    fun `check horizontal bingo test`() {
        val board = Day04.GameBoard(singleBoard)

        assertEquals(false, board.checkBingo())

        board.markNumber(18)
        board.markNumber(8)
        board.markNumber(23)
        board.markNumber(26)
        board.markNumber(20)

        assertEquals(true, board.checkBingo())
    }

    @Test
    fun `unmarked sum test`() {
        val board = Day04.GameBoard(smallBoard)

        board.markNumber(5)

        val sum = board.unmarkedSum()

        assertEquals(21, sum)
    }
}