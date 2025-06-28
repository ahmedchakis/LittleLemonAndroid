package com.example.littlelemon.composables

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.Home
import com.example.littlelemon.MenuItemRoom
import com.example.littlelemon.Onboarding
import com.example.littlelemon.Profile

@Composable
fun Navigation(
    menuItems: List<MenuItemRoom>,
    categories: List<String>,
    navController: NavHostController,
    startDestination: String,
    initialUserProfile: User,
    onLoginSuccess: (User) -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
        composable(Onboarding.route) {
            OnboardingScreen(

                onNavigateToHome = { userProfile ->
                    onLoginSuccess(userProfile)
                    navController.navigate(Home.route) {
                        popUpTo(Onboarding.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Home.route) {
            HomeScreen(
                menuItems = menuItems,
                categories = categories,
                onNavigateToProfile = { navController.navigate(Profile.route) }
            )
        }
        composable(Profile.route) {
            ProfileScreen(
                user = initialUserProfile, // Pass the initial user data to ProfileScreen
                onNavigateBack = { navController.popBackStack() },
                onLogout = {
                    onLogout()
                    navController.navigate(Onboarding.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}