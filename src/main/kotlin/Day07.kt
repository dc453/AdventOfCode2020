package io.github.dc453

class BagProcessor(input: String = "") {

    // TODO: Convert to tree hierarchy
    val bagRules: List<Bag> = input
        .split("\n")
        .mapIndexed { index, bag -> Bag(bag, index) }

    fun getNumGoldBagHolders(): Int {
        return bagRules
            .last { it.contains.contains("shiny gold") }
            .ruleIndex + 1
    }

}

data class Bag(val instructions: String, val ruleIndex: Int) {
    val parts = instructions.split(" contain ")
    val color = parts[0].replace(" bags", "")
    val contains = parts[1]
}