package com.example.littlelemon.composables

// You can place this in a new file or at the bottom of HomeScreen.kt

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R


@Composable
fun CategoryChips(
    categories: List<String>,
    selectedCategories: List<String>,
    onCategorySelected: (String) -> Unit
) {
    Column {
        Box(modifier =Modifier.height(12.dp))
        LazyRow(
            // Add padding around the entire row of chips
            contentPadding = PaddingValues(horizontal = 16.dp),
            // Add spacing between each chip
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { category ->
                CategoryChip(
                    category = category,
                    isSelected = selectedCategories.contains(category), // Determine if this chip is the selected one
                    onClick = { onCategorySelected(category) } // Notify the parent when clicked
                )
            }
        }

    }

}

@Composable
fun CategoryChip(
    category: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp)) // Rounded corners for the chip
            .background(
                // Use secondary (yellow) for selected, light gray for unselected
                color = if (isSelected) colorResource(id = R.color.secondary) else Color(0xFFEDEFEE)
            )
            .clickable(onClick = onClick) // Make it clickable
            .padding(horizontal = 16.dp, vertical = 8.dp) // Padding inside the chip
    ) {
        Text(
            text = category,
            color = if (isSelected) Color.White else colorResource(id = R.color.primary),
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChipPreview() {
    Column {
        CategoryChip(category = "Starters", isSelected = true, onClick = {})
        Spacer(modifier = Modifier.height(10.dp))
        CategoryChip(category = "Mains", isSelected = false, onClick = {})
    }
}