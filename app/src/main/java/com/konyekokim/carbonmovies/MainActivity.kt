package com.konyekokim.carbonmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.konyekokim.carbonmovies.databinding.ActivityMainBinding
import com.konyekokim.commons.extensions.isDarkTheme

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(binding.toolbar) {
            setupWithNavController(findNavController(R.id.nav_host_fragment))
            setOnMenuItemClickListener {
                val nightMode =
                    if (isDarkTheme()) {
                        AppCompatDelegate.MODE_NIGHT_NO
                    } else {
                        AppCompatDelegate.MODE_NIGHT_YES
                    }
                AppCompatDelegate.setDefaultNightMode(nightMode)
                true
            }
        }
    }
}