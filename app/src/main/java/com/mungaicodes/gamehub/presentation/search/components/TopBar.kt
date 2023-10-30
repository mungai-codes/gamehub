package com.mungaicodes.gamehub.presentation.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungaicodes.gamehub.R

@Composable
fun TopBar(
    searchQuery: String,
    enabled: Boolean,
    supportingText: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit
) {

    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(end = 16.dp), horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = " Game Search",
                fontFamily = FontFamily(Font(R.font.pixelifysans)),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { onValueChange(it) },
            modifier = Modifier.fillMaxWidth(0.8f),
            supportingText = {
                Text(
                    text = supportingText,
                    fontFamily = FontFamily(Font(R.font.pixelifysans)),
                    color = MaterialTheme.colorScheme.primary
                )
            },
            label = {
                Text(
                    text = "Search for game",
                    fontFamily = FontFamily(Font(R.font.kurale_regular))
                )
            },
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }, enabled = enabled) {
                    Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = androidx.compose.ui.text.input.ImeAction.Search,
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (enabled) {
                        onSearch()
                        focusManager.clearFocus()
                    }
                }
            ),
            shape = MaterialTheme.shapes.medium
        )
    }

}