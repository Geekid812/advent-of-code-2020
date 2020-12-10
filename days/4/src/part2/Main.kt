package part2

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
    val fieldValidation = mapOf(
        "byr" to {v: String -> v.toInt() in 1920..2002},
        "iyr" to {v: String -> v.toInt() in 2010..2020},
        "eyr" to {v: String -> v.toInt() in 2020..2030},
        "hgt" to {v: String -> heightCheck(v)},
        "hcl" to {v: String -> hairCheck(v)},
        "ecl" to {v: String -> v in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")},
        "pid" to {v: String -> v.length == 9}
    )

    for (field in fieldValidation.keys) {
        if (field !in passport.keys) {
            return false
        } else {
            val isValid = fieldValidation[field]?.invoke(passport[field].toString())
            if (!isValid!!) {
                return false
            }
        }
    }

    return true
}

fun heightCheck(v: String): Boolean {
    val ranges = mapOf(
        "cm" to 150..193,
        "in" to 59..76
    )
    val len = v.length
    val unit = v.substring(len-2)
    val value = v.substring(0, len-2)

    if (unit !in ranges.keys) return false
    return ranges.getValue(unit).contains(value.toInt())
}

fun hairCheck(v: String): Boolean {
    val chars = v.toCharArray()
    val hex = "0123456789abcdef"
    if (chars[0] != '#') return false

    for (char in chars.slice(1 until chars.size)) {
        if (char !in hex) return false
    }

    return chars.size == 7
}