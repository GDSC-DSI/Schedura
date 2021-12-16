package net.code.gdsc_app
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import net.code.gdsc_app.ui.*

@Suppress("DEPRECATION")
internal class TimeTableAdapter(
    var context: Context,
    fm: FragmentManager,
    var totalTabs: Int
) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
               Monday()
            }
            1 -> {
                Tuesday()
            }
            2 -> {
                Wednesday()
            }
            3 -> {
                Thursday()
            }
            4 -> {
                Friday()
            }
            5 -> {
                Saturday()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}