package com.example.littlelemon.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.littlelemon.MenuItemRoom
import com.example.littlelemon.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItemsList(items: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        items(items) { menuItem ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp).drawBehind {
                    val stroke = 1.dp.toPx()
                    drawLine(
                        color = Color.LightGray,
                        start = Offset(0f, size.height - stroke/2),
                        end   = Offset(size.width, size.height - stroke/2),
                        strokeWidth = stroke
                    )
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                // <-- this Column now only takes *remaining* space
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    Text(
                        text = menuItem.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = menuItem.description, color = Color.Gray)
                    Text(text = "$%.2f".format(menuItem.price), color = Color.Gray, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 8.dp))
                }

                // fixed-size box for your GlideImage
                Box(
                    modifier = Modifier
                        .size(60.dp, 70.dp)
                        .clip(RoundedCornerShape(10.dp))
                ) {
                    GlideImage(
                        model = menuItem.image,
                        contentDescription = menuItem.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                        failure = placeholder(R.drawable.hero_image)
                    )
                }
            }
        }
    }
}
