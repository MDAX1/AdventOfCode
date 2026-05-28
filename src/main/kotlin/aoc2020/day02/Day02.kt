package aoc2020.day02

import java.io.File

fun main() {
    val rader = läsInput()

    var giltigaDel1 = 0
    var giltigaDel2 = 0

    for (rad in rader) {
        val policy = parseRad(rad)

        if (ärGiltigDel1(policy)) {
            giltigaDel1++
        }

        if (ärGiltigDel2(policy)) {
            giltigaDel2++
        }
    }

    println("Del 1: $giltigaDel1")
    println("Del 2: $giltigaDel2")
}

data class PasswordPolicy(
    val min: Int, val max: Int, val bokstav: Char, val lösenord: String
)

fun läsInput(): List<String> {
    return File("src/main/resources/day02.txt").readLines()
}

fun parseRad(rad: String): PasswordPolicy {
    val delar = rad.split(" ")

    val nummer = delar[0].split("-")
    val min = nummer[0].toInt()
    val max = nummer[1].toInt()

    val bokstav = delar[1][0]
    val lösenord = delar[2]

    return PasswordPolicy(min, max, bokstav, lösenord)
}

fun ärGiltigDel1(policy: PasswordPolicy): Boolean {
    val antal = policy.lösenord.count { it == policy.bokstav }

    return antal in policy.min..policy.max
}

fun ärGiltigDel2(policy: PasswordPolicy): Boolean {
    val förstaMatch = policy.lösenord[policy.min - 1] == policy.bokstav
    val andraMatch = policy.lösenord[policy.max - 1] == policy.bokstav

    return förstaMatch xor andraMatch
}
