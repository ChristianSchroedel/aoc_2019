object OpCode {
    const val Add = 1
    const val Multiply = 2
    const val Halt = 99
}

fun main() {
    val program = mutableListOf(1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,10,19,1,19,5,23,2,23,6,27,1,27,5,31,2,6,31,35,1,5,35,39,2,39,9,43,1,43,5,47,1,10,47,51,1,51,6,55,1,55,10,59,1,59,6,63,2,13,63,67,1,9,67,71,2,6,71,75,1,5,75,79,1,9,79,83,2,6,83,87,1,5,87,91,2,6,91,95,2,95,9,99,1,99,6,103,1,103,13,107,2,13,107,111,2,111,10,115,1,115,6,119,1,6,119,123,2,6,123,127,1,127,5,131,2,131,6,135,1,135,2,139,1,139,9,0,99,2,14,0,0)
//    program.compute()

//    println("program result: $program")

    determineVerbAndNounFor(program, 19690720)
}

fun determineVerbAndNounFor(input: MutableList<Int>, expectedResult: Int) {
    for (noun in (0..99)) {
        for (verb in (0..99)) {
            val initialInput = input.toMutableList()

            initialInput[0] = 1
            initialInput[1] = noun
            initialInput[2] = verb
            initialInput[3] = 0
            initialInput.compute()

            if (initialInput.first() == expectedResult) {
                println("noun: $noun, verb: $verb")
                println("solution: ${noun * 100 + verb}")
                break
            }
        }
    }
}

fun MutableList<Int>.compute() {
    var opCode = 0
    var programIndex = 0

    while (opCode != OpCode.Halt) {
        opCode = get(programIndex)

        if (opCode == OpCode.Add) {
            opAdd(programIndex + 1, programIndex + 2, programIndex + 3)
        } else if (opCode == OpCode.Multiply) {
            opMultiply(programIndex + 1, programIndex + 2, programIndex + 3)
        }

        programIndex += 4
    }
}

fun MutableList<Int>.opAdd(indexSumA: Int, indexSumB: Int, indexRes: Int) {
    this[get(indexRes)] = opValue(indexSumA) + opValue(indexSumB)
}

fun MutableList<Int>.opMultiply(indexFactorA: Int, indexFactorB: Int, indexRes: Int) {
    this[get(indexRes)] = opValue(indexFactorA) * opValue(indexFactorB)
}

fun MutableList<Int>.opValue(index: Int): Int {
    return this[get(index)]
}
