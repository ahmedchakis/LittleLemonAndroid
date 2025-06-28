package com.example.littlelemon

interface Destinations {
    val route: String
    val title: String
}

object Onboarding : Destinations {
    override val route: String = "onboarding_route"
    override val title: String = "Welcome"
}

object Home : Destinations {
    override val route: String = "home_route"
    override val title: String = "Home"
}

object Profile : Destinations {
    override val route: String = "profile_route"
    override val title: String = "Profile"
}