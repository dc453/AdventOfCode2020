package io.github.dc453

class PasswordValidator(val input: String = "", val useNewPolicy: Boolean = false) {

    fun getValidStatus(passwordInstructions: String = ""): Boolean {
        val parts = passwordInstructions.split(Regex("(-|:?\\s+)+"))
        val min = parts[0].toInt()
        val max = parts[1].toInt()
        val char = parts[2].single()
        val password = parts[3]

        if (useNewPolicy) {
            var matchCount = 0
            if (password[min-1] == char) {
                matchCount++
            }
            if (password[max-1] == char) {
                matchCount++
            }
            return matchCount == 1
        }

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