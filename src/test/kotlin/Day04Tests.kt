import io.github.dc453.PassportValidator
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day04Tests {

    val fullInput = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" +
                "byr:1937 iyr:2017 cid:147 hgt:183cm\n" +
                "\n" +
                "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n" +
                "hcl:#cfa07d byr:1929\n" +
                "\n" +
                "hcl:#ae17e1 iyr:2013\n" +
                "eyr:2024\n" +
                "ecl:brn pid:760753108 byr:1931\n" +
                "hgt:179cm\n" +
                "\n" +
                "hcl:#cfa07d eyr:2025 pid:166559648\n" +
                "iyr:2011 ecl:brn hgt:59in"

    @Test
    fun `should read passports from input`() {
        val validator = PassportValidator(fullInput)
        val expected = "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n" +
                "hcl:#cfa07d byr:1929"
        assertEquals(expected, validator.passports[1])
    }

    @Test
    fun `should require all fields`() {
        val validator = PassportValidator("byr:1 iyr:2 eyr:3 hgt:4 hcl:5 ecl:6 pid:7")
        val result = validator.isValid(validator.passports[0])
        assertEquals(true, result)
    }

    @Test
    fun `should fail if no birth year`() {
        val validator = PassportValidator("iyr:2 eyr:3 hgt:4 hcl:5 ecl:6 pid:7")
        val result = validator.isValid(validator.passports[0])
        assertEquals(false, result)
    }

    @Test
    fun `should fail if no issue year`() {
        val validator = PassportValidator("byr:1 eyr:3 hgt:4 hcl:5 ecl:6 pid:7")
        val result = validator.isValid(validator.passports[0])
        assertEquals(false, result)
    }

    @Test
    fun `should fail if no expiration year`() {
        val validator = PassportValidator("byr:1 iyr:2 hgt:4 hcl:5 ecl:6 pid:7")
        val result = validator.isValid(validator.passports[0])
        assertEquals(false, result)
    }

    @Test
    fun `should fail if no height`() {
        val validator = PassportValidator("byr:1 iyr:2 eyr:3 hcl:5 ecl:6 pid:7")
        val result = validator.isValid(validator.passports[0])
        assertEquals(false, result)
    }

    @Test
    fun `should fail if no hair color`() {
        val validator = PassportValidator("byr:1 iyr:2 eyr:3 hgt:4 ecl:6 pid:7")
        val result = validator.isValid(validator.passports[0])
        assertEquals(false, result)
    }

    @Test
    fun `should fail if no eye color`() {
        val validator = PassportValidator("byr:1 iyr:2 eyr:3 hgt:4 hcl:5 pid:7")
        val result = validator.isValid(validator.passports[0])
        assertEquals(false, result)
    }

    @Test
    fun `should fail if no passport id`() {
        val validator = PassportValidator("byr:1 iyr:2 eyr:3 hgt:4 hcl:5 ecl:6")
        val result = validator.isValid(validator.passports[0])
        assertEquals(false, result)
    }

    @Test
    fun `should count valid passports`() {
        val validator = PassportValidator(fullInput)
        val result = validator.
        getValidPassports()
        assertEquals(2, result)
    }

}