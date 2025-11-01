package io.github.dc453

const val ROW_MIN = 0
const val ROW_MAX = 127
const val COL_MIN = 0
const val COL_MAX = 7

class SeatFinder(input: String = "") {

    val boardingPasses: List<String> = input.split("\n")

    var rowMin: Int = ROW_MIN
    var rowMax: Int = ROW_MAX
    var colMin: Int = COL_MIN
    var colMax: Int = COL_MAX

    fun process(code: Char) {
        when (code) {
            'F' -> {
                rowMax = ((rowMax - rowMin) / 2) + rowMin
            }
            'B' -> {
                rowMin += ((rowMax - rowMin) / 2) + 1
            }
            'L' -> {
                colMax = ((colMax - colMin) / 2) + colMin
            }
            'R' -> {
                colMin += ((colMax - colMin) / 2) + 1
            }
        }
    }

    fun findSeat(instructions: String): Int {
        resetPosition()
        instructions.forEach { process(it) }
        return (rowMin * 8) + colMin
    }

    fun findHighestSeat(): Int {
        return getFilledSeats().maxOf { it }
    }

    private fun getFilledSeats(): List<Int> = boardingPasses
        .map { findSeat(it) }
        .sorted()

    fun findSeatWithoutBoardingPass(seats: List<Int> = listOf()): Int {
        val filledSeats = seats.ifEmpty { getFilledSeats() }
        filledSeats.forEach { seat ->
            if (!filledSeats.contains(seat + 1) && filledSeats.contains(seat + 2)) {
                return seat + 1
            }
        }
        return 0
    }


    private fun resetPosition() {
        rowMin = ROW_MIN
        rowMax = ROW_MAX
        colMin = COL_MIN
        colMax = COL_MAX
    }

}