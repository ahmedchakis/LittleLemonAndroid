package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.content.edit
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room

import com.example.littlelemon.composables.Navigation
import com.example.littlelemon.composables.SharedPreferencesKeys
import com.example.littlelemon.composables.User
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LittleLemonTheme {
                val navController = rememberNavController()
                val prefs = getSharedPreferences("little lemon", MODE_PRIVATE)
                var userProfile by remember {
                    mutableStateOf(
                        User(
                            firstName = prefs.getString(SharedPreferencesKeys.FIRST_NAME, "") ?: "",
                            lastName = prefs.getString(SharedPreferencesKeys.LAST_NAME, "") ?: "",
                            email = prefs.getString(SharedPreferencesKeys.EMAIL, "") ?: ""
                        )
                    )
                }
                val loggedIn = prefs.getBoolean("loggedIn", false)

                val start = if(loggedIn) Home.route else Onboarding.route


                val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())

                val uniqueCategories by remember(databaseMenuItems) {
                    derivedStateOf {
                        databaseMenuItems.map { it.category }.toSet().toList()
                    }
                }











                Navigation( navController = navController,
                    initialUserProfile = userProfile,
                    menuItems = databaseMenuItems,
                    categories = uniqueCategories,



                onLoginSuccess = { newUser ->
                        prefs.edit().apply {
                            putBoolean(SharedPreferencesKeys.LOGGED_IN, true)
                            putString(SharedPreferencesKeys.FIRST_NAME, newUser.firstName)
                            putString(SharedPreferencesKeys.LAST_NAME, newUser.lastName)
                            putString(SharedPreferencesKeys.EMAIL, newUser.email)
                        }.apply()
                    userProfile = newUser
                    },
                    onLogout = {
                               prefs.edit().clear().apply()
                    },
                    startDestination = start,


                    )

            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuItemDao().isEmpty()) {
                val menuItems = fetchMenu()
                saveMenuToDatabase(menuItems)
            }
        }



    }
    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        val response =
            httpClient.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
        val menuNetwork = response.body<MenuNetwork>()
        return menuNetwork.menu
    }

    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }


}

