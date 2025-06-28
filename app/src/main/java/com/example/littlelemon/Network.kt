package com.example.littlelemon

import android.view.MenuItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetwork(
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    val id: Int,
    val title: String,
    var price: Double,
    val description: String,
    val image: String,
    val category: String,
) {
    fun toMenuItemRoom() = MenuItemRoom(
        id,
        title,
        price,
        description, image, category
    )
}
