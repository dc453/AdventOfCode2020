import io.github.dc453.TobogganMap
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day03Tests {

    val testInput = "..##.......\n" +
                    "#...#...#..\n" +
                    ".#....#..#.\n" +
                    "..#.#...#.#\n" +
                    ".#...##..#.\n" +
                    "..#.##.....\n" +
                    ".#.#.#....#\n" +
                    ".#........#\n" +
                    "#.##...#...\n" +
                    "#...##....#\n" +
                    ".#..#...#.#"

    @Test
    fun `should not count open spaces`() {
        val map = TobogganMap(testInput)
        val result = map.stepAndCheckForTree()
        assertEquals(false, result)
    }

    @Test
    fun `should count trees`() {
        val map = TobogganMap(testInput)

        map.stepAndCheckForTree()
        val result = map.stepAndCheckForTree()

        assertEquals(true, result)
    }

    @Test
    fun `should count all trees in the path`() {
        val map = TobogganMap(testInput)
        val result = map.countTrees()
        assertEquals(7, result)
    }

    @Test
    fun `should accept travel path overrides`() {
        val map = TobogganMap(testInput)
        val result = map.countTrees(1, 1)
        assertEquals(2, result)
    }

    @Test
    fun `should count trees on all slopes`() {
        val map = TobogganMap(testInput)
        val result = map.countTreesOnAllSlopes()
        assertEquals(336, result)
    }
}