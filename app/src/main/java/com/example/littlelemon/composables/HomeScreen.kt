package com.example.littlelemon.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.littlelemon.AppDatabase
import com.example.littlelemon.MenuItemRoom
import com.example.littlelemon.R // Make sure this points to your R file
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json

// Assuming R.drawable.logo is your app's logo
// You might also have R.drawable.user_avatar for a specific user image

@Composable
fun HomeScreen(onNavigateToProfile: () -> Unit, menuItems: List<MenuItemRoom>, categories: List<String>,) {

    var selectedCategories = remember { mutableStateListOf   <String> () }

    var searchText by remember { mutableStateOf("") }

    val filteredMenuItems = menuItems.filter { menuItem ->
        val matchesCategory = selectedCategories.isEmpty() || selectedCategories.contains(menuItem.category)
        val matchesSearch = searchText.isBlank() || menuItem.title.contains(searchText, ignoreCase = true)
        matchesCategory && matchesSearch
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Header Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.Center)
                )


                CircularAvatar(
                    onNavigateToProfile = onNavigateToProfile,
                    modifier = Modifier.align(Alignment.CenterEnd),
                    avatarPainter = painterResource(id = R.drawable.baseline_person_24)
                )
            }
        }
        HeroSection(searchText = searchText, onSearchTextChange = {
            searchText = it
        })

        CategoryChips(
            categories = categories,
            selectedCategories = selectedCategories,
            onCategorySelected = { newCategory ->
                if(selectedCategories.contains((newCategory))){
                    selectedCategories.remove(newCategory)
                } else{
                    selectedCategories.add(newCategory)
                }

            }
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {

            MenuItemsList(items = filteredMenuItems)
        }
    }
}


/**
 * Reusable Composable for a circular avatar.
 *
 * @param modifier Modifier to be applied to the avatar.
 * @param size The size (width and height) of the avatar.
 * @param avatarPainter Optional custom painter for the avatar image. If null, a default icon is used.
 * @param onNavigateToProfile Lambda to be invoked when the avatar is clicked.
 */
@Composable
fun CircularAvatar(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    avatarPainter: Painter? = null,
    onNavigateToProfile: () -> Unit
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(colorResource(id = R.color.secondary))
            .clickable(onClick = onNavigateToProfile),
        contentAlignment = Alignment.Center
    ) {
        if (avatarPainter != null) {
            Image(
                painter = avatarPainter,
                contentDescription = "User Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Default Avatar",
                tint = Color.White,
                modifier = Modifier.size(size * 0.7f)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme { // Wrap your preview in your app's theme
        HomeScreen(onNavigateToProfile = {}, menuItems = emptyList(), categories = emptyList())
    }
}

@Preview(showBackground = true)
@Composable
fun CircularAvatarPreview() {
    MaterialTheme {
        Column {
            CircularAvatar(onNavigateToProfile = {}) // Default avatar
            Spacer(modifier = Modifier.height(10.dp))
            // Example with a placeholder image (replace with your actual user avatar drawable)
            // Assuming you have a drawable named 'user_avatar'
            // com.example.littlelemon.composables.CircularAvatar(avatarPainter = painterResource(id = R.drawable.user_avatar), onNavigateToProfile = {})
        }
    }
}