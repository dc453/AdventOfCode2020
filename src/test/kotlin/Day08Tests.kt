import io.github.dc453.BootCode
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day08Tests {

    @Test
    fun `should default accumulator to 0`() {
        val bootCode = BootCode()
        assertEquals(0, bootCode.accumulator)
    }

    @Test
    fun `should increment accumulator with positive acc instruction`() {
        val bootCode = BootCode("acc +2")
        assertEquals(2, bootCode.accumulator)
    }

    @Test
    fun `should decrement accumulator with negative acc instruction`() {
        val bootCode = BootCode(
            "acc +2\n" +
                    "acc -1"
        )
        assertEquals(1, bootCode.accumulator)
    }

    @Test
    fun `should skip noop instructions`() {
        val bootCode = BootCode(
            "noop +5\n" +
                    "acc +2"
        )
        assertEquals(2, bootCode.accumulator)
    }

    @Test
    fun `should process positive jump instructions`() {
        val bootCode = BootCode(
            "jmp +2\n" +
                    "acc +1\n" +
                    "acc +1\n"
        )
        assertEquals(1, bootCode.accumulator)
    }

}