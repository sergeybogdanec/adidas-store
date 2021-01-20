package com.sergeybogdanec.adidasstore.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.sergeybogdanec.adidasstore.R
import com.sergeybogdanec.adidasstore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment


        setUpNavigation(navHostFragment.navController)
    }

    private fun setUpNavigation(controller: NavController) {
        with (binding) {
            bottomNavigationView.setupWithNavController(controller)
        }
    }
}
