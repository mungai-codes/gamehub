package com.mungaicodes.gamehub.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> ResultGrid(
    items: List<T>,
    modifier: Modifier = Modifier,
    content: @Composable (T) -> Unit
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(3),
        modifier = modifier
            .height((3 * 270).dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) { item ->
            content(item)
        }
    }
}

@Composable
fun ResultGridShimmer(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    if (isLoading) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = modifier
                .height((3 * 270).dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(15) {
                GridItemShimmer()
            }
        }
    } else {
        content()
    }
}

