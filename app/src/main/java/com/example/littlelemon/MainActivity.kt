package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.edit
import androidx.navigation.compose.rememberNavController

import com.example.littlelemon.composables.Navigation
import com.example.littlelemon.composables.SharedPreferencesKeys
import com.example.littlelemon.composables.User
import com.example.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LittleLemonTheme {
                val navController = rememberNavController()
                val prefs = getSharedPreferences("little lemon", MODE_PRIVATE)
                val initialUserProfile = User(
                    firstName = prefs.getString(SharedPreferencesKeys.FIRST_NAME, "") ?: "",
                    lastName = prefs.getString(SharedPreferencesKeys.LAST_NAME, "") ?: "",
                    email = prefs.getString(SharedPreferencesKeys.EMAIL, "") ?: ""
                )
                val loggedIn = prefs.getBoolean("loggedIn", false)

                val start = if(loggedIn) Home.route else Onboarding.route


                Navigation(navController = navController,initialUserProfile = initialUserProfile,
                    onLoginSuccess = { userProfile ->
                        prefs.edit().apply {
                            putBoolean(SharedPreferencesKeys.LOGGED_IN, true)
                            putString(SharedPreferencesKeys.FIRST_NAME, userProfile.firstName)
                            putString(SharedPreferencesKeys.LAST_NAME, userProfile.lastName)
                            putString(SharedPreferencesKeys.EMAIL, userProfile.email)
                        }.apply()
                    },
                    onLogout = {
                               prefs.edit().clear().apply()
                    },
                    startDestination = start

                    )

            }
        }


    }


}

