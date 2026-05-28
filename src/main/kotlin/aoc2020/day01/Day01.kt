package aoc2020.day01

import java.io.File

private const val TARGET = 2020

fun main() {
    val nummer = läsInput()

    val del1 = hittaTvåTal(nummer)
    val del2 = hittaTreTal(nummer)

    println("Del 1: $del1")
    println("Del 2: $del2")
}

fun läsInput(): List<Int> {
    return File("src/main/resources/day01.txt").readLines().map { it.toInt() }
}

fun hittaTvåTal(nummer: List<Int>): Int {
    for (i in nummer.indices) {
        for (j in i + 1 until nummer.size) {

            val första = nummer[i]
            val andra = nummer[j]

            if (första + andra == TARGET) {
                return första * andra
            }
        }
    }

    error("Hittade inga två tal")
}

fun hittaTreTal(nummer: List<Int>): Int {
    for (i in nummer.indices) {
        for (j in i + 1 until nummer.size) {
            for (k in j + 1 until nummer.size) {

                val första = nummer[i]
                val andra = nummer[j]
                val tredje = nummer[k]

                if (första + andra + tredje == TARGET) {
                    return första * andra * tredje
                }
            }
        }
    }

    error("Hittade inga tre tal")
}
