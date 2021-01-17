package by.tix.draft.artemapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.tix.draft.artemapp.R
import by.tix.draft.artemapp.databinding.ActivityAuthBinding

class AuthActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
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
