package tj.tajsoft.loyalrsn.presintation.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEachIndexed
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.R
import tj.tajsoft.loyalrsn.app.App
import tj.tajsoft.loyalrsn.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BottomNavigationVisibilityListener {
    private lateinit var binding: ActivityMainBinding
    private val navController get() = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    private val viewModel by viewModels<MainViewModel>()
    private var currentNavItemId: Int = R.id.homeFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resetKillTimer()

        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (!it.isSuccessful){
                return@addOnCompleteListener
            }
            val token = it.result
           viewModel.saveTokenUser(token)

        }
        binding.apply {
            navigation.setupWithNavController(navController)
            navigation.setOnItemSelectedListener {

                if (currentNavItemId!=it.itemId){
                    currentNavItemId = it.itemId
                    navController.navigate(it.itemId)
                    true
                } else {
                    false
                }

//                var index: Int = 0
//                navigation.menu.forEachIndexed { itemIndex, item ->
//                    if (it.itemId != item.itemId) return@forEachIndexed
//                    index = itemIndex
//                }
//                NavigationUI.onNavDestinationSelected(it, navController)
//                return@setOnItemSelectedListener true
            }
            navController.addOnDestinationChangedListener { _, destination, _ ->
                navigation.isVisible =
                    listOf(
                        R.id.profileFragment,
                        R.id.splashScreenFragment,
                        R.id.checkNumberFragment,
                        R.id.otpFragment,
                        R.id.registerOneFragment,
                        R.id.registerTwoFragment,
                        R.id.logInFragment,
                        R.id.QRCodeFragment,
                        R.id.notificationFragment,
                        R.id.detailDiscountFragment,
                        R.id.multiCardFragment,
                        R.id.userTransactionFragment,
                        R.id.allUserFragment
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
    override fun onResume() {
        super.onResume()
        resetKillTimer()
    }
    override fun onUserInteraction() {
        super.onUserInteraction()
        resetKillTimer()
    }
    private fun resetKillTimer() {
        val app = application as App
        app.resetKillTimer()
    }
}