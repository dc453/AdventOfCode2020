package io.github.dc453

class CustomsDeclarationForm(val input: String = "", val unanimousMode: Boolean = false) {

    fun getNumYesAnswers(group: String): Int {
        val uniqueAnswers = group.toSet()
            .filter { it != '\n' }

        if (unanimousMode) {
            var numUnanimous: Int = 0
            uniqueAnswers.forEach { answer ->
                if (group.filter { it == answer }.length == group.split("\n").size) {
                    numUnanimous += 1
                }
            }
            return numUnanimous
        }

        return uniqueAnswers.size
    }

    fun getNumYesAnswersForGroups(): List<Int> {
        return input.split("\n\n")
            .map { getNumYesAnswers(it) }
    }

    fun getTotalYesAnswers(): Int {
        return getNumYesAnswersForGroups().sum()
    }

}