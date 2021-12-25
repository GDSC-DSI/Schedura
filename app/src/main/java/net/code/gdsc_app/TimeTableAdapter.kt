package net.code.gdsc_app
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import net.code.gdsc_app.models.Query
import net.code.gdsc_app.ui.*
import net.code.gdsc_app.utils.Constants

@Suppress("DEPRECATION")
internal class TimeTableAdapter(
    var context: Context,
    fm: FragmentManager,
    var totalTabs: Int,
    val query: Query
) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
               WeekDayFragment.newInstance(query,Constants.Companion.WeekDay.MONDAY)
            }
            1 -> {
                WeekDayFragment.newInstance(query,Constants.Companion.WeekDay.TUESDAY)
            }
            2 -> {
                WeekDayFragment.newInstance(query,Constants.Companion.WeekDay.WEDNESDAY)
            }
            3 -> {
                WeekDayFragment.newInstance(query,Constants.Companion.WeekDay.THURSDAY)
            }
            4 -> {
                WeekDayFragment.newInstance(query,Constants.Companion.WeekDay.FRIDAY)
            }
            5 -> {
                WeekDayFragment.newInstance(query,Constants.Companion.WeekDay.SATURDAY)
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}