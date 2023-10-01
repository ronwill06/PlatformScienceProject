package com.williams.platformscience.extensions

fun String.countNumOfVowels(): Int {
    val vowels = listOf('a', 'e', 'i', 'o', 'u')
    var count = 0
    for (character in lowercase()) {
        if (character in vowels) {
            count++
        }
    }
    return count
}

fun String.countNumOfConsonants(): Int {
    val vowelCount = countNumOfVowels()
    return length - vowelCount
}