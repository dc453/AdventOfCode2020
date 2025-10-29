package io.github.dc453

class TobogganMap(input: String) {

    private val map = input.split("\n")
    private var x: Int = 0
    private var y: Int = 0

    fun stepAndCheckForTree(moveX: Int = 3, moveY: Int = 1): Boolean {
        x += moveX
        y += moveY
        if (x > map[y].lastIndex) {
            x -= map[y].lastIndex + 1
        }

        return map[y][x] == '#'
    }

    fun countTrees(moveX: Int = 3, moveY: Int = 1): Int {
        x = 0
        y = 0
        var numTrees = 0
        while (y < map.lastIndex) {
            numTrees += if (stepAndCheckForTree(moveX, moveY)) 1 else 0
        }
        return numTrees
    }

    fun countTreesOnAllSlopes(): Long {
        val slopes = listOf(
            Slope(1, 1),
            Slope(3, 1),
            Slope(5, 1),
            Slope(7, 1),
            Slope(1, 2)
        )
        val trees = mutableListOf<Long>()
        slopes.forEach { slope ->
            trees.add(countTrees(slope.moveX, slope.moveY).toLong())
        }
        return trees.reduce { accumulator, element -> accumulator * element }
    }

}

data class Slope(val moveX: Int, val moveY: Int)