package day01
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day01KtTest {

    @Test
    fun `part 1 sample set reports correct increase`() {
        val input = listOf(
            "199",
            "200",
            "208",
            "210",
            "200",
            "207",
            "240",
            "269",
            "260",
            "263"
        )
        assertEquals(7, Day01.part1countLarger(input))
    }

    @Test
    fun `part 2 test`() {
        val input = listOf(
            "199",
            "200",
            "208",
            "210",
            "200",
            "207",
            "240",
            "269",
            "260",
            "263"
        )

        assertEquals(5, Day01.part2CountLarger(input))
    }
}