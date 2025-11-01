package io.github.dc453

class PassportValidator(input: String = "") {

    val passports = input.split("\n\n")

    fun isValid(passport: String): Boolean {
        return  passport.contains("byr:") &&
                passport.contains("iyr:") &&
                passport.contains("eyr:") &&
                passport.contains("hgt:") &&
                passport.contains("hcl:") &&
                passport.contains("ecl:") &&
                passport.contains("pid:")
    }

    fun isValidStrict(passport: String): Boolean {
        if (!isValid(passport)) {
            return false
        }

        val parts = passport.split(Regex("\\s"))
        var numValid = 0

        fun trackValidity(isValid: Boolean) {
            if (isValid) numValid += 1
        }

        parts.forEach {
            val (key, value) = it.split(":")
            when (key) {
                "byr" -> trackValidity(isValidYear(value) && value.toInt() in 1920 ..2002)
                "iyr" -> trackValidity(isValidYear(value) && value.toInt() in 2010 ..2020)
                "eyr" -> trackValidity(isValidYear(value) && value.toInt() in 2020 ..2030)
                "hgt" -> {
                    if (!value.contains("cm") && !value.contains("in")) {
                        return@forEach
                    }

                    val height = value.substring(0, value.lastIndex - 1).toInt()
                    val unit = value.substring(value.lastIndex - 1)

                    trackValidity(when (unit) {
                            "cm" -> height in 150..193
                            "in" -> height in 59..76
                            else -> false
                        })
                }
                "hcl" -> trackValidity(value.matches(Regex("#([0-9]|[a-f]){6}")))
                "ecl" -> trackValidity(value.matches(Regex("(amb)|(blu)|(brn)|(gry)|(grn)|(hzl)|(oth)")))
                "pid" -> trackValidity(value.matches(Regex("^[0-9]{9}\$")))
            }
        }
        return numValid == 7
    }

    private fun isValidYear(value: String): Boolean = value.length == 4

    fun getValidPassports(): Int {
        return passports.count { isValid(it) }
    }

    fun getValidStrictPassports(): Int {
        return passports.count { isValidStrict(it) }
    }

}