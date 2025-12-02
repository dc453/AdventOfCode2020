package io.github.dc453

class BootCode(input: String = "noop +0") {

    var accumulator: Int = 0

    init {
        val instructions = input.split("\n")
        val iterator = instructions.listIterator()


//            .forEach {
                val (instruction, value) = it.split(" ")
                if (instruction == "acc") {
                    accumulator += value.toInt()
                } else if (instruction == "jmp") {

                }
//            }
    }

}