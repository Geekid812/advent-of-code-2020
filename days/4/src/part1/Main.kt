package part1

import java.io.File

fun main() {
    val content = File("input.txt").readText()
    val lines = content.replace(" ", "\r\n")

    var validPassports = 0
    var passportCount = 0
    val passport = mutableMapOf<String, String>()

    for (line in lines.split("\r\n")) {
        if (line.isBlank()) {
            // End of passport data
            val isValid = checkPassport(passport)
            if (isValid) {
                validPassports++
            }
            passportCount++
            passport.clear()
            continue
        }

        // Add a field
        val pair = line.split(':')
        passport[pair[0]] = pair[1]
    }

    println("There are $validPassports valid passports out of $passportCount.")
}

fun checkPassport(passport: MutableMap<String, String>): Boolean {
    val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    for (field in requiredFields) {
        if (field !in passport.keys) {
            return false
        }
    }

    return true
}