package com.rochapires.coffeebrewer.util

import android.text.format.DateUtils

object Utils {
    @JvmStatic
    fun String.isValidQuantity(): Boolean {
        val regex = """^(\d+(?:[\.\,]\d{1,2})?)${'$'}""".toRegex()
        return regex.matches(this)
    }

    fun Long.formatDuration(shouldShowUnit: Boolean = true): String {
        return if (this < 60) {
            """${this}${if(shouldShowUnit)'s' else ""}"""
        } else {
            DateUtils.formatElapsedTime(this)
        }
    }
}
