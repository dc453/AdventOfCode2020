import io.github.dc453.PasswordValidator
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day02Tests {

    @Test
    fun `should fail if minimum not met`() {
        val validator = PasswordValidator()
        val result = validator.getValidStatus("1-3 a: bbcde")
        assertEquals(false, result)
    }

    @Test
    fun `should pass if minimum is met`() {
        val validator = PasswordValidator()
        val result = validator.getValidStatus("1-3 a: abcde")
        assertEquals(true, result)
    }

    @Test
    fun `should fail if maximum is exceeded`() {
        val validator = PasswordValidator()
        val result = validator.getValidStatus("1-3 a: aaaab")
        assertEquals(false, result)
    }

    @Test
    fun `should get number of valid passwords from list`() {
        val validator = PasswordValidator("1-3 a: abcde\n" +
                "1-3 b: cdefg\n" +
                "2-9 c: ccccccccc")
        val result = validator.getNumValidPasswords()
        assertEquals(2, result)
    }

    @Nested
    @DisplayName("when new policy enabled")
    inner class WhenNewPolicyEnabled {

        @Test
        fun `should pass if found in first slot`() {
            val validator = PasswordValidator("", true)
            val result = validator.getValidStatus("1-3 a: abcde")
            assertEquals(true, result)
        }

        @Test
        fun `should pass if found in second slot`() {
            val validator = PasswordValidator("", true)
            val result = validator.getValidStatus("1-3 a: cbade")
            assertEquals(true, result)
        }

        @Test
        fun `should fail if found in both slots`() {
            val validator = PasswordValidator("", true)
            val result = validator.getValidStatus("1-3 a: abade")
            assertEquals(false, result)
        }

        @Test
        fun `should fail if found in neither slot`() {
            val validator = PasswordValidator("", true)
            val result = validator.getValidStatus("1-3 b: cdefg")
            assertEquals(false, result)
        }

        @Test
        fun `should get number of valid passwords`() {
            val validator = PasswordValidator("1-3 a: abcde\n" +
                    "1-3 b: cdefg\n" +
                    "2-9 c: ccccccccc",
                true)
            val result = validator.getNumValidPasswords()
            assertEquals(1, result)
        }

    }

}