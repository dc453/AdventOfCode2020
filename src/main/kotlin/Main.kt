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

}