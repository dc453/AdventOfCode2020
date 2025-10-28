package io.github.dc453

class TobogganMap(input: String) {

    private val map = input.split("\n")

    var numTrees: Int = 0
    var x: Int = 0
    var y: Int = 0

    fun step() {
        x += 3
        y += 1
        if (x > map[y].lastIndex) {
            x -= map[y].lastIndex + 1
        }

        if (map[y][x] == '#') {
            numTrees += 1
        }
    }

    fun countTrees() {
        while (y < map.lastIndex) {
            step()
        }
    }

}