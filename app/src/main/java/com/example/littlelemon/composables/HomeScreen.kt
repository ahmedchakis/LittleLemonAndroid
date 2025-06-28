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
import com.example.littlelemon.R // Make sure this points to your R file

// Assuming R.drawable.logo is your app's logo
// You might also have R.drawable.user_avatar for a specific user image

@Composable
fun HomeScreen(onNavigateToProfile: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Header Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
                .padding(horizontal = 16.dp), // Padding for the whole header
            verticalAlignment = Alignment.CenterVertically // Ensures items are vertically centered in the row
        ) {
            // Use a Box to manage the positioning of the logo and avatar within the Row
            Box(
                modifier = Modifier.fillMaxWidth() // This Box takes up all available width in the Row
            ) {
                // Logo - Centered in the Box
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(200.dp) // Adjusted size for better header fit
                        .align(Alignment.Center) // Aligns this Image to the center of its parent Box
                )

                // Circular Avatar - Aligned to the right-center of the Box
                CircularAvatar(
                    onNavigateToProfile = onNavigateToProfile,
                    modifier = Modifier.align(Alignment.CenterEnd),
                    avatarPainter = painterResource(id = R.drawable.baseline_person_24)
                )
            }
        }

        // Rest of your screen content goes here
        Box(
            modifier = Modifier
                .weight(1f) // This makes the content box fill the remaining vertical space
                .fillMaxWidth()
                .background(Color.LightGray) // Just for visualization
                .padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text("Your main content goes here!")
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
    size: Dp = 48.dp, // Default size for the avatar
    avatarPainter: Painter? = null, // You can pass your user's image painter here
    onNavigateToProfile: () -> Unit
) {
    Box(
        modifier = modifier
            .size(size) // Set the overall size of the avatar container
            .clip(CircleShape) // Clip the content to a circle
            .background(colorResource(id = R.color.secondary)) // Background color for the avatar (or fallback for default icon)
            .clickable(onClick = onNavigateToProfile), // Make it clickable
        contentAlignment = Alignment.Center // Center content (image/icon) within the circle
    ) {
        if (avatarPainter != null) {
            Image(
                painter = avatarPainter,
                contentDescription = "User Avatar",
                contentScale = ContentScale.Crop, // Crop to fill the circle nicely
                modifier = Modifier.fillMaxSize()
            )
        } else {
            // Fallback: Default Person icon if no specific avatar image is provided
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Default Avatar",
                tint = Color.White, // Color of the icon
                modifier = Modifier.size(size * 0.7f) // Make icon slightly smaller than the circle
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme { // Wrap your preview in your app's theme
        HomeScreen(onNavigateToProfile = {})
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
            // CircularAvatar(avatarPainter = painterResource(id = R.drawable.user_avatar), onNavigateToProfile = {})
        }
    }
}