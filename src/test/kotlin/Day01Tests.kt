import io.github.dc453.ExpenseReport
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day01Tests {

    val input = "1721\n" +
            "979\n" +
            "366\n" +
            "299\n" +
            "675\n" +
            "1456"
    val expenseReport = ExpenseReport(input)

    @Test
    fun `should read expense report`() {
        assertEquals(1721, expenseReport.report[0])
    }

    @Test
    fun `should multiply items that sum to 2020`() {
        val result = expenseReport.getMultiple()
        assertEquals(514579, result)
    }

}