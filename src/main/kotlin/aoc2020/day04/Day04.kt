package aoc2020.day04

import java.io.File

fun main() {
    val text = File("src/main/resources/day04.txt").readText()
    val block = text.trim().split("\n\n")

    var giltigaDel1 = 0
    var giltigaDel2 = 0

    for (passBlock in block) {
        val pass = parsePass(passBlock)

        if (harAllaFält(pass)) {
            giltigaDel1++
        }

        if (harAllaFält(pass) && allaVärdenGiltiga(pass)) {
            giltigaDel2++
        }
    }

    println("Del 1: $giltigaDel1")
    println("Del 2: $giltigaDel2")
}

fun parsePass(block: String): Map<String, String> {
    val pass = mutableMapOf<String, String>()

    val delar = block.split(Regex("\\s+"))

    for (del in delar) {
        val nyckel = del.substringBefore(":")
        val värde = del.substringAfter(":")

        pass[nyckel] = värde
    }

    return pass
}

fun harAllaFält(pass: Map<String, String>): Boolean {
    val obligatoriska = listOf(
        "byr",
        "iyr",
        "eyr",
        "hgt",
        "hcl",
        "ecl",
        "pid"
    )

    for (fält in obligatoriska) {
        if (fält !in pass) {
            return false
        }
    }

    return true
}

fun allaVärdenGiltiga(pass: Map<String, String>): Boolean {
    val byr = pass["byr"]!!
    val iyr = pass["iyr"]!!
    val eyr = pass["eyr"]!!
    val hgt = pass["hgt"]!!
    val hcl = pass["hcl"]!!
    val ecl = pass["ecl"]!!
    val pid = pass["pid"]!!

    val byrNummer = byr.toIntOrNull() ?: return false
    if (byr.length != 4 || byrNummer !in 1920..2002) {
        return false
    }

    val iyrNummer = iyr.toIntOrNull() ?: return false
    if (iyr.length != 4 || iyrNummer !in 2010..2020) {
        return false
    }

    val eyrNummer = eyr.toIntOrNull() ?: return false
    if (eyr.length != 4 || eyrNummer !in 2020..2030) {
        return false
    }

    if (hgt.endsWith("cm")) {
        val cm = hgt.removeSuffix("cm").toIntOrNull() ?: return false

        if (cm !in 150..193) {
            return false
        }

    } else if (hgt.endsWith("in")) {
        val inch = hgt.removeSuffix("in").toIntOrNull() ?: return false

        if (inch !in 59..76) {
            return false
        }

    } else {
        return false
    }

    if (!hcl.matches(Regex("#[0-9a-f]{6}"))) {
        return false
    }

    val ögonFärger = listOf(
        "amb",
        "blu",
        "brn",
        "gry",
        "grn",
        "hzl",
        "oth"
    )

    if (ecl !in ögonFärger) {
        return false
    }

    if (!pid.matches(Regex("[0-9]{9}"))) {
        return false
    }

    return true
}

