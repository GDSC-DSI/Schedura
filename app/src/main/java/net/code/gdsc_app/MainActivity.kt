package net.code.gdsc_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import net.code.gdsc_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        window.setStatusBarColor(this.getResources().getColor(R.color.material_blue))
        val navHost = findViewById<View>(R.id.navHostFragmentHome)
        binding.bottomNav.setupWithNavController(navHost.findNavController())
    }
}