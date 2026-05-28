// Advent of Code 2020 – Dag 1
// Hitta tal som summerar till 2020

package aoc2020.day01

import java.io.File

private const val TARGET = 2020

fun main() {
    val numbers = läsInput()

    println("=== Advent of Code 2020 – Dag 1 ===\n")

    val delA = hittaTvåTal(numbers)
    val delB = hittaTreTal(numbers)

    println("Del a: $delA")
    println("Del b: $delB")
}

// Läser in tal från fil
fun läsInput(): List<Int> {
    return File("src/main/resources/day01.txt")
        .readLines()
        .map(String::toInt)
}

// Del a - två tal som blir 2020
fun hittaTvåTal(numbers: List<Int>): Int {
    for (i in numbers.indices) {
        for (j in i + 1 until numbers.size) {

            val first = numbers[i]
            val second = numbers[j]

            if (first + second == TARGET) {
                return first * second
            }
        }
    }

    error("Inga två tal summerar till $TARGET")
}

// Del b - tre tal som blir 2020
fun hittaTreTal(numbers: List<Int>): Int {
    for (i in numbers.indices) {
        for (j in i + 1 until numbers.size) {
            for (k in j + 1 until numbers.size) {

                val first = numbers[i]
                val second = numbers[j]
                val third = numbers[k]

                if (first + second + third == TARGET) {
                    return first * second * third
                }
            }
        }
    }

    error("Inga tre tal summerar till $TARGET")
}
