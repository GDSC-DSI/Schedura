package net.code.gdsc_app.utils

import net.code.gdsc_app.R

class Constants {

    companion object{
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        enum class WeekDay{
            MONDAY,
            TUESDAY,
            WEDNESDAY,
            THURSDAY,
            FRIDAY,
            SATURDAY
        }

        fun getRandomCardColor() : Int{
            val cardColors : Array<Int> = arrayOf(
                R.color.cardColor1,
                R.color.cardColor2,
                R.color.cardColor3,
                R.color.cardColor4,
                R.color.cardColor5,
                R.color.cardColor6)
            return cardColors.random()
        }
    }
}