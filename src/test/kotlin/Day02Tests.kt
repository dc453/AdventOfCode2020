import io.github.dc453.PasswordValidator
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

}