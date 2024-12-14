package com.example.biasaaja_companyprofile.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.biasaaja_companyprofile.R
import com.example.biasaaja_companyprofile.databinding.ActivityMainBinding
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import com.example.biasaaja_companyprofile.util.SessionManager
import com.example.studentproject.util.createNotificationChannel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var sessionManager: SessionManager
    init {
        instance = this
    }

    companion object {
        private var instance:MainActivity ?= null
        fun showNotification(title:String, content:String, icon:Int) {
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"
            val notificationBuilder =
                NotificationCompat.Builder(instance!!.applicationContext,
                    channelId).apply {
                    setSmallIcon(icon)
                    setContentTitle(title)
                    setContentText(content)
                    setStyle(NotificationCompat.BigTextStyle())
                    priority = NotificationCompat.PRIORITY_DEFAULT
                    setAutoCancel(true)
                }



            val notificationManager =
                NotificationManagerCompat.from(instance!!.applicationContext)

            if (ActivityCompat.checkSelfPermission(instance!!.applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS) !=
                PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(instance!!,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),1)
                return
            }

            notificationManager.notify(1001,
                notificationBuilder.build())

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        if (!sessionManager.isLoggedIn()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(
            this, navController,
            binding.drawerLayout
        )

        NavigationUI.setupWithNavController(binding.navView, navController)
        binding.bottomNav.setupWithNavController(navController)

        updateWelcomeMessage()

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            handleMenuClick(menuItem)
        }

    }

    private fun updateWelcomeMessage() {
        val menu = binding.navView.menu
        val welcomeItem = menu.findItem(R.id.itemName)
        val firstName = sessionManager.getFirstName() ?: "Guest"
        val lastName = sessionManager.getLastName() ?: ""
        welcomeItem.title = "Welcome, $firstName $lastName"
    }

    private fun handleMenuClick(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.itemApply -> {
                // Apply Team
                val action =
                    HomeFragmentDirections.actionHomeToApplyTeamFragment(sessionManager.getUsername() ?: "")

                navController.navigate(action)
            }
            R.id.itemSignOut -> {
                // Clear session and navigate to login
                sessionManager.clearSession()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START) // Close the drawer
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, binding.drawerLayout) ||
                super.onSupportNavigateUp()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            1 -> {
                if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    Log.d("permission", "granted")
                    createNotificationChannel(this,
                        NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
                        getString(R.string.app_name), "App notification channel.")

                } else {
                    Log.d("permission", "deny")
                }

            }
        }
    }
}