package com.mungaicodes.gamehub.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun ColumnItemsShimmer(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    contentAfterLoading: @Composable () -> Unit,
) {

    if (isLoading) {
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Surface(
                modifier = Modifier
                    .size(150.dp, 15.dp)
                    .padding(start = 16.dp),
                shape = MaterialTheme.shapes.extraSmall
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .shimmerEffect()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement
                    .spacedBy(6.dp)
            ) {
                repeat(5) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            modifier = Modifier
                                .size(54.dp),
                            shape = MaterialTheme.shapes.small
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .shimmerEffect()
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Column(modifier = Modifier.weight(0.5f)) {
                            Surface(
                                modifier = Modifier
                                    .size(Random.nextInt(150, 160).dp, 15.dp),
                                shape = MaterialTheme.shapes.extraSmall
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .shimmerEffect()
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Surface(
                                modifier = Modifier
                                    .size(Random.nextInt(80, 150).dp, 13.dp),
                                shape = MaterialTheme.shapes.extraSmall
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .shimmerEffect()
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Surface(
                            modifier = Modifier
                                .size(35.dp),
                            shape = MaterialTheme.shapes.extraSmall
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .shimmerEffect()
                            )
                        }
                    }
                }
            }
        }
    } else {
        contentAfterLoading()
    }
}