import io.github.dc453.SeatFinder
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day05Tests {

    @Test
    fun `should read the list of boarding passes`() {
        val finder = SeatFinder("FFBFBBBRLR\n" +
            "BBFFBFFRRR\n" +
            "BFFFFBBRRR\n" +
            "FFBFFBBLRR")
        assertEquals("FFBFBBBRLR", finder.boardingPasses[0])
    }

    @Test
    fun `should process reading front rows`() {
        val finder = SeatFinder()
        finder.process('F')
        assertEquals(0, finder.rowMin)
        assertEquals(63, finder.rowMax)
    }

    @Test
    fun `should process reading back rows`() {
        val finder = SeatFinder()

        finder.process('B')

        assertEquals(64, finder.rowMin)
        assertEquals(127, finder.rowMax)
    }

    @Test
    fun `should process multiple row instructions`() {
        val finder = SeatFinder()

        finder.findSeat("FBFB")

        assertEquals(40, finder.rowMin)
        assertEquals(47, finder.rowMax)
    }

    @Test
    fun `should process reading right columns`() {
        val finder = SeatFinder()

        finder.process('R')

        assertEquals(4, finder.colMin)
        assertEquals(7, finder.colMax)
    }

    @Test
    fun `should process reading left columns`() {
        val finder = SeatFinder()

        finder.process('L')

        assertEquals(0, finder.colMin)
        assertEquals(3, finder.colMax)
    }

    @Test
    fun `should process multiple column instructions`() {
        val finder = SeatFinder()

        finder.findSeat("RLR")

        assertEquals(5, finder.colMin)
        assertEquals(5, finder.colMax)
    }

    @Test
    fun `should find seat ID`() {
        val finder = SeatFinder()
        val result = finder.findSeat("FBFBBFFRLR")
        assertEquals(357, result)
    }

    @Test
    fun `should find highest seat ID`() {
        val finder = SeatFinder("FBFBBFFRLR\n" +
            "BFFFBBFRRR\n" +
            "FFFBBBFRRR\n" +
            "BBFFBBFRLL")
        val result = finder.findHighestSeat()
        assertEquals(820, result)
    }

    @Test
    fun `should locate seat without boarding pass`() {
        val finder = SeatFinder()
        val result = finder.findSeatWithoutBoardingPass(listOf(1, 2, 4, 5))
        assertEquals(3, result)
    }

}