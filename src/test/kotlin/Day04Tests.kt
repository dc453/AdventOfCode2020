import io.github.dc453.PassportValidator
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
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
        val result = validator.getValidPassports()
        assertEquals(2, result)
    }

    @Nested
    @DisplayName("when strict mode enabled")
    inner class StrictModeTests {

        @Test
        fun `should return valid if all criteria met`() {
            val validator = PassportValidator()
            val result = validator.isValidStrict("iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719")
            assertEquals(true, result)
        }

        @Test
        fun `should fail if missing criteria`() {
            val validator = PassportValidator()
            val result = validator.isValidStrict("hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719")
            assertEquals(false, result)
        }

        @Test
        fun `should require birth year to be 4 digits`() {
            val validator = PassportValidator()
            val result = validator.isValidStrict("byr:194 iyr:2010 hgt:158cm hcl:#b6652a ecl:blu eyr:2021 pid:093154719")
            assertEquals(false, result)
        }

        @Test
        fun `should require birth year to be at least 1920`() {
            val validator = PassportValidator()

            val expectedFail = validator.isValidStrict("byr:1919 iyr:2010 hgt:158cm hcl:#b6652a ecl:blu eyr:2021 pid:093154719")
            assertEquals(false, expectedFail)

            val expectedPass = validator.isValidStrict("byr:1920 iyr:2010 hgt:158cm hcl:#b6652a ecl:blu eyr:2021 pid:093154719")
            assertEquals(true, expectedPass)
        }

        @Test
        fun `should require birth year to be at most 2002`() {
            val validator = PassportValidator()

            val expectedFail = validator.isValidStrict("byr:2003 iyr:2010 hgt:158cm hcl:#b6652a ecl:blu eyr:2021 pid:093154719")
            assertEquals(false, expectedFail)

            val expectedPass = validator.isValidStrict("byr:2002 iyr:2010 hgt:158cm hcl:#b6652a ecl:blu eyr:2021 pid:093154719")
            assertEquals(true, expectedPass)
        }

        @Test
        fun `should require issue year to be 4 digits`() {
            val validator = PassportValidator()
            val result = validator.isValidStrict("iyr:201 byr:1942 hgt:158cm hcl:#b6652a ecl:blu eyr:2021 pid:093154719")
            assertEquals(false, result)
        }

        @Test
        fun `should require issue year to be at least 2010`() {
            val validator = PassportValidator()

            val expectedFail = validator.isValidStrict("iyr:2009 byr:1950 hgt:158cm hcl:#b6652a ecl:blu eyr:2021 pid:093154719")
            assertEquals(false, expectedFail)

            val expectedPass = validator.isValidStrict("iyr:2010 byr:1950 hgt:158cm hcl:#b6652a ecl:blu eyr:2021 pid:093154719")
            assertEquals(true, expectedPass)
        }

        @Test
        fun `should require issue year to be at most 2020`() {
            val validator = PassportValidator()

            val expectedFail = validator.isValidStrict("iyr:2021 byr:1950 hgt:158cm hcl:#b6652a ecl:blu eyr:2021 pid:093154719")
            assertEquals(false, expectedFail)

            val expectedPass = validator.isValidStrict("iyr:2020 byr:1950 hgt:158cm hcl:#b6652a ecl:blu eyr:2021 pid:093154719")
            assertEquals(true, expectedPass)
        }

        @Test
        fun `should require expiration year to be 4 digits`() {
            val validator = PassportValidator()
            val result = validator.isValidStrict("eyr:202 byr:1942 iyr:2010 hgt:158cm hcl:#b6652a ecl:blu pid:093154719")
            assertEquals(false, result)
        }

        @Test
        fun `should require expiration year to be at least 2020`() {
            val validator = PassportValidator()

            val expectedFail = validator.isValidStrict("eyr:1967 byr:1942 iyr:2010 hgt:158cm hcl:#b6652a ecl:blu pid:093154719")
            assertEquals(false, expectedFail)

            val expectedPass = validator.isValidStrict("eyr:2020 byr:1942 iyr:2010 hgt:158cm hcl:#b6652a ecl:blu pid:093154719")
            assertEquals(true, expectedPass)
        }

        @Test
        fun `should require expiration year to be at most 2030`() {
            val validator = PassportValidator()

            val expectedFail = validator.isValidStrict("eyr:2031 byr:1942 iyr:2010 hgt:158cm hcl:#b6652a ecl:blu pid:093154719")
            assertEquals(false, expectedFail)

            val expectedPass = validator.isValidStrict("eyr:2030 byr:1942 iyr:2010 hgt:158cm hcl:#b6652a ecl:blu pid:093154719")
            assertEquals(true, expectedPass)
        }

        @Test
        fun `should require height to be at least 150 when measured in cm`() {
            val validator = PassportValidator()

            val expectedFail = validator.isValidStrict("hgt:149cm eyr:2025 byr:1942 iyr:2010 hcl:#b6652a ecl:blu pid:093154719")
            assertEquals(false, expectedFail)

            val expectedPass = validator.isValidStrict("hgt:150cm eyr:2025 byr:1942 iyr:2010 hcl:#b6652a ecl:blu pid:093154719")
            assertEquals(true, expectedPass)
        }

        @Test
        fun `should require height to be at most 193 when measured in cm`() {
            val validator = PassportValidator()

            val expectedFail = validator.isValidStrict("hgt:194cm eyr:2025 byr:1942 iyr:2010 hcl:#b6652a ecl:blu pid:093154719")
            assertEquals(false, expectedFail)

            val expectedPass = validator.isValidStrict("hgt:193cm eyr:2025 byr:1942 iyr:2010 hcl:#b6652a ecl:blu pid:093154719")
            assertEquals(true, expectedPass)
        }

        @Test
        fun `should require height to be at least 59 when measured in in`() {
            val validator = PassportValidator()

            val expectedFail = validator.isValidStrict("hgt:58in eyr:2025 byr:1942 iyr:2010 hcl:#b6652a ecl:blu pid:093154719")
            assertEquals(false, expectedFail)

            val expectedPass = validator.isValidStrict("hgt:59in eyr:2025 byr:1942 iyr:2010 hcl:#b6652a ecl:blu pid:093154719")
            assertEquals(true, expectedPass)
        }

        @Test
        fun `should require height to be at most 76 when measured in in`() {
            val validator = PassportValidator()

            val expectedFail = validator.isValidStrict("hgt:77in eyr:2025 byr:1942 iyr:2010 hcl:#b6652a ecl:blu pid:093154719")
            assertEquals(false, expectedFail)

            val expectedPass = validator.isValidStrict("hgt:76in eyr:2025 byr:1942 iyr:2010 hcl:#b6652a ecl:blu pid:093154719")
            assertEquals(true, expectedPass)
        }

        @Test
        fun `should require hair color to be a valid hex value`() {
            val validator = PassportValidator()

            val expectedFail = validator.isValidStrict("hcl:b6652a hgt:70in eyr:2025 byr:1942 iyr:2010 ecl:blu pid:093154719")
            assertEquals(false, expectedFail)

            val expectedPass = validator.isValidStrict("hcl:#b6652a hgt:70in eyr:2025 byr:1942 iyr:2010 ecl:blu pid:093154719")
            assertEquals(true, expectedPass)
        }

        @Test
        fun `should require eye color to be a valid option`() {
            val validator = PassportValidator()

            val expectedFail = validator.isValidStrict("ecl:foo hcl:#b6652a hgt:70in eyr:2025 byr:1942 iyr:2010 pid:093154719")
            assertEquals(false, expectedFail)

            val validOptions = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
            validOptions.forEach {
                assertEquals(true, validator.isValidStrict("ecl:$it hcl:#b6652a hgt:70in eyr:2025 byr:1942 iyr:2010 pid:093154719"), "validating eye color $it")
            }
        }

        @Test
        fun `should require passport ID to be 9 digits`() {
            val validator = PassportValidator()

            val expectedMinFail = validator.isValidStrict("pid:00132181 ecl:amb hcl:#b6652a hgt:70in eyr:2025 byr:1942 iyr:2010")
            assertEquals(false, expectedMinFail)

            val expectedMaxFail = validator.isValidStrict("pid:0041281831 ecl:amb hcl:#b6652a hgt:70in eyr:2025 byr:1942 iyr:2010")
            assertEquals(false, expectedMaxFail)

            val expectedPass = validator.isValidStrict("pid:004123128 ecl:amb hcl:#b6652a hgt:70in eyr:2025 byr:1942 iyr:2010")
            assertEquals(true, expectedPass)
        }

        @Test
        fun `should count all strict valid passports`() {
            val validator = PassportValidator("eyr:1972 cid:100\n" +
                    "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926\n" +
                    "\n" +
                    "iyr:2019\n" +
                    "hcl:#602927 eyr:1967 hgt:170cm\n" +
                    "ecl:grn pid:012533040 byr:1946\n" +
                    "\n" +
                    "pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980\n" +
                    "hcl:#623a2f\n" +
                    "\n" +
                    "eyr:2029 ecl:blu cid:129 byr:1989\n" +
                    "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm\n" +
                    "\n" +
                    "hcl:#888785\n" +
                    "hgt:164cm byr:2001 iyr:2015 cid:88\n" +
                    "pid:545766238 ecl:hzl\n" +
                    "eyr:2022")
            val result = validator.getValidStrictPassports()
            assertEquals(3, result)
        }

    }

}