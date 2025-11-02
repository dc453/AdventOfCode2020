import io.github.dc453.CustomsDeclarationForm
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day06Tests {

    @Test
    fun `should count unique yes answers in the group`() {
        val form = CustomsDeclarationForm()
        val result = form.getNumYesAnswers("ab\n" +
                "ac")
        assertEquals(3, result)
    }

    @Test
    fun `should count unique yes answers for all groups`() {
        val form = CustomsDeclarationForm("ab\n" +
                "ac\n" +
                "\n" +
                "a\n" +
                "a\n" +
                "a\n" +
                "a\n" +
                "\n" +
                "b")

        val result = form.getNumYesAnswersForGroups()

        assertEquals(3, result[0])
        assertEquals(1, result[1])
        assertEquals(1, result[2])
    }

    @Test
    fun `should total number of unique yes answers in group`() {
        val form = CustomsDeclarationForm("abc\n" +
                "\n" +
                "a\n" +
                "b\n" +
                "c\n" +
                "\n" +
                "ab\n" +
                "ac\n" +
                "\n" +
                "a\n" +
                "a\n" +
                "a\n" +
                "a\n" +
                "\n" +
                "b")
        val result = form.getTotalYesAnswers()
        assertEquals(11, result)
    }

    @Test
    fun `should total number of universal yes answers in group`() {
        val form = CustomsDeclarationForm("abc\n" +
                "\n" +
                "a\n" +
                "b\n" +
                "c\n" +
                "\n" +
                "ab\n" +
                "ac\n" +
                "\n" +
                "a\n" +
                "a\n" +
                "a\n" +
                "a\n" +
                "\n" +
                "b",
            true)
        val result = form.getTotalYesAnswers()
        assertEquals(6, result)
    }

}