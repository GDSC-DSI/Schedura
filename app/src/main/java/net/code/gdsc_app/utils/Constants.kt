package net.code.gdsc_app.utils

import net.code.gdsc_app.R

class Constants {

    companion object {
        const val BASE_URL = "https://gdsc-dsi.herokuapp.com/"

        enum class WeekDay {
            MONDAY,
            TUESDAY,
            WEDNESDAY,
            THURSDAY,
            FRIDAY,
            SATURDAY
        }

        val timingsMap: Map<Int, Map<Int, String>> = mapOf(
            0 to mapOf(0 to "9:00", 1 to "10:00"),
            1 to mapOf(0 to "10:00", 1 to "11:00"),
            2 to mapOf(0 to "11:15", 1 to "12:15"),
            3 to mapOf(0 to "12:15", 1 to "13:15"),
            4 to mapOf(0 to "14:00", 1 to "15:00"),
            5 to mapOf(0 to "15:00", 1 to "16:00"),
            6 to mapOf(0 to "16:00", 1 to "17:00"),
        )

        val semToYearMap: Map<String, String> =
            mapOf("1st" to "21", "2nd" to "20", "3rd" to "19", "4th" to "18")

        fun getRandomCardColor(): Int {
            val cardColors: Array<Int> = arrayOf(
                R.color.cardColor1,
                R.color.cardColor2,
                R.color.cardColor3,
                R.color.cardColor4,
                R.color.cardColor5,
                R.color.cardColor6,
                R.color.cardColor7,
                R.color.cardColor8,
                R.color.cardColor9,
                R.color.cardColor10,
                R.color.cardColor11,
                R.color.cardColor12,
                R.color.cardColor13,
                R.color.cardColor14,
                R.color.cardColor15
            )
            return cardColors.random()
        }
    }
}