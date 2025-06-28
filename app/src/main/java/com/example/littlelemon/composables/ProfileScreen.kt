package com.example.littlelemon.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R
import com.example.littlelemon.composables.User // Import the data class

@Composable
fun ProfileScreen(
    user: User, // New parameter to receive user data
    onNavigateBack: () -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    var firstName by remember(user.firstName) {
        mutableStateOf(user.firstName)
    }

    var lastName by remember(user.lastName) {
        mutableStateOf(user.lastName)
    }

    var email by remember(user.email) {
        mutableStateOf(user.email)
    }

    var firstNameError by remember {
        mutableStateOf("")
    }

    var lastNameError by remember {
        mutableStateOf("")
    }

    var emailError by remember {
        mutableStateOf("")
    }
    var isError by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding (horizontal = 16.dp),

    ) {
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
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center, modifier = modifier
                .fillMaxSize()){
            Text(text = "Profile", style = androidx.compose.material3.MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(32.dp))

            CustomTextField(label = "First Name", onChangeValue = { firstName = it }, value = firstName, isError = isError, errorMessage = firstNameError)

            Spacer(modifier = Modifier.padding(all= 5.dp))
            CustomTextField(label = "Last Name", onChangeValue = { lastName = it }, value = lastName,isError = isError, errorMessage = lastNameError)

            Spacer(modifier = Modifier.padding(all= 5.dp))
            CustomTextField(label = "Email", onChangeValue = { email = it }, value = email,isError = isError, errorMessage = emailError)



            androidx.compose.material.Text(
                text = "Go Back",
                textAlign = TextAlign.Center, // Centers horizontally
                modifier = Modifier
                    .width(120.dp)
                    .border(
                        width = 1.dp, // You can adjust the border thickness
                        color = Color.Black, // Black border
                        shape = RoundedCornerShape(20.dp) // Apply radius to the border
                    )
                    .height(60.dp)
                    .background(
                        color = colorResource(id = R.color.secondary),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .wrapContentHeight(align = Alignment.CenterVertically)
                    .clickable {
                        onNavigateBack()
                    }

            )

            Spacer(modifier = Modifier.height(8.dp))

            androidx.compose.material.Text(
                text = "Log out",
                textAlign = TextAlign.Center, // Centers horizontally
                modifier = Modifier
                    .width(120.dp)
                    .border(
                        width = 1.dp, // You can adjust the border thickness
                        color = Color.Black, // Black border
                        shape = RoundedCornerShape(20.dp) // Apply radius to the border
                    )
                    .height(60.dp)
                    .background(
                        color = colorResource(id = R.color.secondary),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .wrapContentHeight(align = Alignment.CenterVertically)
                    .clickable {
                        onLogout()
                    }

            )

        }


    }
}