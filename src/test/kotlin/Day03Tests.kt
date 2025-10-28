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
        map.step()
        assertEquals(0, map.numTrees)
    }

    @Test
    fun `should count trees`() {
        val map = TobogganMap(testInput)

        map.step()
        map.step()

        assertEquals(1, map.numTrees)
    }

    @Test
    fun `should count all trees in the path`() {
        val map = TobogganMap(testInput)
        map.countTrees()
        assertEquals(7, map.numTrees)
    }
}