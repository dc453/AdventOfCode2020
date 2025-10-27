package io.github.dc453

class PasswordValidator(val input: String = "") {

    fun getValidStatus(passwordInstructions: String = ""): Boolean {
        val parts = passwordInstructions.split(Regex("(-|:?\\s+)+"))
        val min = parts[0].toInt()
        val max = parts[1].toInt()
        val char = parts[2].single()
        val password = parts[3]

        val match = password.filter { it == char }

        return match.count() in min..max
    }

    fun getNumValidPasswords(): Int {
        var numValid = 0
        input.split("\n")
            .forEach { password ->
                if (getValidStatus(password)) {
                    numValid += 1
                }
            }
        return numValid
    }

}