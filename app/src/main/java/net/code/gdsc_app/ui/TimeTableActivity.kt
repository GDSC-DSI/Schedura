package net.code.gdsc_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager.widget.ViewPager
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import net.code.gdsc_app.R
import net.code.gdsc_app.TimeTableAdapter
import net.code.gdsc_app.models.Query
import net.code.gdsc_app.networking.Repository
import net.code.gdsc_app.viewmodels.TimeTableViewModel

class TimeTableActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    private val repository by lazy {
        Repository()
    }
    private val args : TimeTableActivityArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table)

        val toolbar : MaterialToolbar = findViewById<MaterialToolbar>(R.id.toolbarDashboard)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val query : Query = args.query


        supportActionBar?.hide()
//        setTheme(R.style.AppTheme)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        tabLayout.addTab(tabLayout.newTab().setText("Monday"))
        tabLayout.addTab(tabLayout.newTab().setText("Tuesday"))
        tabLayout.addTab(tabLayout.newTab().setText("Wednesday"))
        tabLayout.addTab(tabLayout.newTab().setText("Thursday"))
        tabLayout.addTab(tabLayout.newTab().setText("Friday"))
        tabLayout.addTab(tabLayout.newTab().setText("Saturday"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = TimeTableAdapter(this, supportFragmentManager, tabLayout.tabCount, query)
        val window: Window = this.window
        window.statusBarColor = ContextCompat.getColor(this, R.color.transparent)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}
