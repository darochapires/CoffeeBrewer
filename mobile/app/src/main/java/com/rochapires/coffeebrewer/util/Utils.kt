package com.rochapires.coffeebrewer.util

object Utils {
    @JvmStatic
    fun isValidQuantity(input: String): Boolean {
        val regex = """^(\d+(?:[\.\,]\d{1,2})?)${'$'}""".toRegex()
        return regex.matches(input)
    }
}
