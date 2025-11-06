import io.github.dc453.BagProcessor
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day07Tests {

    val testInput = "light red bags contain 1 bright white bag, 2 muted yellow bags.\n" +
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags.\n" +
            "bright white bags contain 1 shiny gold bag.\n" +
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.\n" +
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.\n" +
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags.\n" +
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.\n" +
            "faded blue bags contain no other bags.\n" +
            "dotted black bags contain no other bags."

    @Test
    fun `should parse bag type definitions`() {
        val processor = BagProcessor(testInput)
        assertEquals("light red", processor.bagRules[0].color)
        assertEquals("dark orange", processor.bagRules[1].color)
    }

    @Test
    fun `should count how many bags can contain gold bags`() {
        val processor = BagProcessor(testInput)
        val result = processor.getNumGoldBagHolders()
        assertEquals(4, result)
    }

}