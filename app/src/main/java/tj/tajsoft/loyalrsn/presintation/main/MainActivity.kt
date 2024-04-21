package tj.tajsoft.loyalrsn.presintation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.forEachIndexed
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import tajsoft.demoproject.myapplication.presintation.main.BottomNavigationVisibilityListener
import tj.tajsoft.loyalrsn.R
import tj.tajsoft.loyalrsn.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BottomNavigationVisibilityListener {
    private lateinit var binding: ActivityMainBinding
    private val navController get() = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            navigation.setupWithNavController(navController)
            navigation.setOnItemSelectedListener {
                var index: Int = 0
                navigation.menu.forEachIndexed { itemIndex, item ->
                    if (it.itemId != item.itemId) return@forEachIndexed
                    index = itemIndex
                }
                NavigationUI.onNavDestinationSelected(it, navController)
                return@setOnItemSelectedListener true
            }
            navController.addOnDestinationChangedListener { _, destination, _ ->
                navigation.isVisible =
                    listOf(
                        R.id.profileFragment,
                        R.id.splashScreenFragment,
                        R.id.checkNumberFragment,
                        R.id.otpFragment,
                        R.id.registerOneFragment,
                        R.id.registerTwoFragment
                    ).all {
                        it != destination.id
                    }

            }
        }
    }


    override fun toggleBottomNavigationVisibility(visibility: Boolean) {
        if (visibility) {
             binding.navigation.visibility = View.VISIBLE
        } else {
             binding.navigation.visibility = View.GONE
        }
    }
}