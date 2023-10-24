package com.mungaicodes.gamehub.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungaicodes.gamehub.R

@Composable
fun RatingCard(rating: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(35.dp)
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.primaryContainer), MaterialTheme.shapes.small)
    ) {
        Text(
            text = rating,
            modifier = Modifier.align(Alignment.Center),
            fontFamily = FontFamily(Font(R.font.tiltneon_regular)),
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}