package io.github.dc453

import java.io.File

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    // Day 1
    val day1Input = File("src/main/inputs/Day01.txt").readText()
    val expenseReport = ExpenseReport(day1Input)
    val day1Part1 = expenseReport.getMultiple()
    println("Day 1, Part 1: $day1Part1")
    val day1Part2 = expenseReport.getMultipleOf3()
    println("Day 1, Part 2: $day1Part2")

    // Day 2
    val day2Input = File("src/main/inputs/Day02.txt").readText()
    val passwordValidator = PasswordValidator(day2Input)
    val day2Part1 = passwordValidator.getNumValidPasswords()
    println("Day 2, Part 1: $day2Part1")
    val passwordValidatorWithNewPolicy = PasswordValidator(day2Input, true)
    val day2Part2 = passwordValidatorWithNewPolicy.getNumValidPasswords()
    println("Day 2, Part 2: $day2Part2")

    // Day 3
    val day3Input = File("src/main/inputs/Day03.txt").readText()
    val tobogganMap = TobogganMap(day3Input)
    val day3Part1 = tobogganMap.countTrees()
    println("Day 3, Part 1: $day3Part1")
    val day3Part2 = tobogganMap.countTreesOnAllSlopes()
    println("Day 3, Part 2: $day3Part2")

}