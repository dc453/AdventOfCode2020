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

    fun getValidPassports(): Int {
        return passports.count { isValid(it) }
    }

}