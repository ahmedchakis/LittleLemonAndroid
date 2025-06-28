package com.example.littlelemon.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R // Make sure this points to your R file

@Composable
fun HeroSection(  searchText: String,
                  onSearchTextChange: (String) -> Unit) {


    // State for the search text field

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.primary)) // Dark green background
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        // Main Title
        Text(
            text = "Little Lemon",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.secondary) // Yellow color
        )

        // Subtitle and Description/Image Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp), // Adjust padding as needed
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left side: Subtitle and Description
            Column(
                modifier = Modifier
                    .weight(1f) // Takes up available space, pushing image to the right
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = "Chicago",
                    fontSize = 24.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

            // Right side: Image Placeholder
            Box(
                modifier = Modifier
                    .size(120.dp, 160.dp)
                    .clip(RoundedCornerShape(10.dp))

            ){
                Image(painter = painterResource(id = R.drawable.hero_image), contentDescription = "Hero image")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search Bar
        OutlinedTextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            placeholder = {
                Text(text = "Enter search phrase")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color(0xFFE0E0E0), // Light gray background for the text field
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeroSectionPreview() {
    // Create a temporary state for the preview only
    var text by remember { mutableStateOf("") }
    HeroSection(
        searchText = text,
        onSearchTextChange = { newText -> text = newText }
    )
}