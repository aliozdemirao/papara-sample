package com.aliozdemir.paparasample

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initEvents()
    }

    private fun initViews() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        drawerLayout = findViewById(R.id.drawerLayout)
        toolbar = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.navigationView)

        appBarConfiguration =
            AppBarConfiguration(
                setOf(
                    R.id.dashboardFragment,
                    R.id.qrDialogFragment,
                    R.id.moneyTransferDialogFragment,
                    R.id.paymentFragment,
                    R.id.paparaCardFragment,
                    R.id.notificationFragment,
                    R.id.searchAtmFragment,
                    R.id.withdrawDepositFragment,
                ),
                drawerLayout,
            )
    }

    private fun initEvents() {
        bottomNavigationView.setupWithNavController(navHostFragment.navController)

        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)
        navigationView.setupWithNavController(navHostFragment.navController)

        onBackPressedDispatcher.addCallback(
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START)
                    } else {
                        finish()
                    }
                }
            },
        )
    }

    override fun onSupportNavigateUp(): Boolean =
        navHostFragment.navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
}
