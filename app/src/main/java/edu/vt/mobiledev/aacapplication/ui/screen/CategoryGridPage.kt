
package edu.vt.mobiledev.aacapplication.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.vt.mobiledev.aacapplication.domain.model.Category
import edu.vt.mobiledev.aacapplication.util.TTS

@Composable
fun CategoryGridScreen(
    categories: List<Category>,
    onCategoryClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = categories,
            key = { it.id }
        ) { category ->

            val categoryColor = when (category.id) {
                1 -> Color(0xFFFFF0F0)
                2 -> Color(0xFFFFD7AF)
                3 -> Color(0xFFF1ECC0)
                4 -> Color(0xFFD8FFD0)
                5 -> Color(0xFFD0D8FF)
                else -> Color.White
            }

            val borderColor = when (category.id) {
                1 -> Color(0xFFFDA8A8)
                2 -> Color(0xFFFFC67A)
                3 -> Color(0xFFF7D85D)
                4 -> Color(0xFFADF18F)
                5 -> Color(0xFF7D6CFF)
                else -> Color.Black
            }

            Card(
                modifier = Modifier
                    .aspectRatio(1f)
                    .clickable {
                        onCategoryClick(category.id)
                        TTS.speak(category.nameOfCategory)
                    },
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                border = BorderStroke(2.dp, borderColor),
                colors = CardDefaults.cardColors(
                    containerColor = categoryColor
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = category.icon),
                            contentDescription = category.nameOfCategory,
                            modifier = Modifier.size(85.dp)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = category.nameOfCategory,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
