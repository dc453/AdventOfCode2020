package io.github.dc453

class CustomsDeclarationForm(val input: String = "") {

    fun getNumYesAnswers(group: String): Int {
        return group.toSet()
            .filter { it != '\n' }
            .size
    }

    fun getNumYesAnswersForGroups(): List<Int> {
        return input.split("\n\n")
            .map { getNumYesAnswers(it) }
    }

    fun getTotalYesAnswers(): Int {
        return getNumYesAnswersForGroups().sum()
    }

}