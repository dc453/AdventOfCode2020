package io.github.dc453

class ExpenseReport(val input: String = "") {

    val report = input
        .split("\n")
        .map { it -> it.toInt() }

    fun getMultiple(): Int {
        report.forEach { line ->
            val difference = 2020 - line
            if (report.contains(difference)) {
                return line * difference
            }
        }
        return 0
    }

    fun getMultipleOf3(): Int {
        report.forEach { itemX ->
            report.forEach { itemY ->
                val difference = 2020 - itemX - itemY
                if (report.contains(difference)) {
                    return itemX * itemY * difference
                }
            }
        }
        return 0
    }

}