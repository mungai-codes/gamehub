package com.mungaicodes.gamehub.presentation.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mungaicodes.gamehub.R
import com.mungaicodes.gamehub.domain.model.DarkThemeConfig
import com.mungaicodes.gamehub.domain.model.DarkThemeConfig.DARK
import com.mungaicodes.gamehub.domain.model.DarkThemeConfig.FOLLOW_SYSTEM
import com.mungaicodes.gamehub.domain.model.DarkThemeConfig.LIGHT
import com.mungaicodes.gamehub.domain.model.ThemeBrand
import com.mungaicodes.gamehub.domain.model.ThemeBrand.ANDROID
import com.mungaicodes.gamehub.domain.model.ThemeBrand.DEFAULT
import com.mungaicodes.gamehub.presentation.theme.supportsDynamicTheming

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {

    val state by viewModel.settingsUiState.collectAsState()

    SettingsScreenContent(
        state = state,
        onChangeThemeBrand = viewModel::updateThemeBrand,
        onChangeDynamicColorPreference = viewModel::updateDynamicColorPreference,
        onChangeDarkThemeConfig = viewModel::updateDarkThemeConfig,
    )

}

@Composable
fun SettingsScreenContent(
    state: SettingsUiState,
    supportDynamicColor: Boolean = supportsDynamicTheming(),
    onChangeThemeBrand: (themeBrand: ThemeBrand) -> Unit,
    onChangeDynamicColorPreference: (useDynamicColor: Boolean) -> Unit,
    onChangeDarkThemeConfig: (darkThemeConfig: DarkThemeConfig) -> Unit,
) {

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "Settings",
                    fontFamily = FontFamily(Font(R.font.pixelifysans)),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    ) { contentPadding ->

        Column(
            Modifier
                .fillMaxSize()
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    bottom = contentPadding.calculateBottomPadding(),
                    start = 16.dp,
                    end = 16.dp
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (state) {
                SettingsUiState.Loading -> {
                    Text(
                        text = "Loading...",
                        modifier = Modifier.padding(vertical = 16.dp),
                        fontFamily = FontFamily(Font(R.font.pixelifysans))
                    )
                }

                is SettingsUiState.Success -> {
                    SettingsPanel(
                        settings = state.settings,
                        supportDynamicColor = supportDynamicColor,
                        onChangeThemeBrand = onChangeThemeBrand,
                        onChangeDynamicColorPreference = onChangeDynamicColorPreference,
                        onChangeDarkThemeConfig = onChangeDarkThemeConfig,
                    )
                }
            }
        }

    }

}

@Composable
private fun ColumnScope.SettingsPanel(
    settings: UserEditableSettings,
    supportDynamicColor: Boolean,
    onChangeThemeBrand: (themeBrand: ThemeBrand) -> Unit,
    onChangeDynamicColorPreference: (useDynamicColor: Boolean) -> Unit,
    onChangeDarkThemeConfig: (darkThemeConfig: DarkThemeConfig) -> Unit,
) {
    SettingsDialogSectionTitle(text = "Settings")
    Column(Modifier.selectableGroup()) {
        SettingsDialogThemeChooserRow(
            text = "Default",
            selected = settings.brand == ThemeBrand.DEFAULT,
            onClick = { onChangeThemeBrand(DEFAULT) },
        )
        SettingsDialogThemeChooserRow(
            text = "Android",
            selected = settings.brand == ANDROID,
            onClick = { onChangeThemeBrand(ANDROID) },
        )
    }
    AnimatedVisibility(visible = settings.brand == ThemeBrand.DEFAULT && supportDynamicColor) {
        Column {
            SettingsDialogSectionTitle(text = "Use Dynamic Color")
            Column(Modifier.selectableGroup()) {
                SettingsDialogThemeChooserRow(
                    text = "Yes",
                    selected = settings.useDynamicColor,
                    onClick = { onChangeDynamicColorPreference(true) },
                )
                SettingsDialogThemeChooserRow(
                    text = "No",
                    selected = !settings.useDynamicColor,
                    onClick = { onChangeDynamicColorPreference(false) },
                )
            }
        }
    }
    SettingsDialogSectionTitle(text = "Dark mode preference")
    Column(Modifier.selectableGroup()) {
        SettingsDialogThemeChooserRow(
            text = "System default",
            selected = settings.darkThemeConfig == FOLLOW_SYSTEM,
            onClick = { onChangeDarkThemeConfig(FOLLOW_SYSTEM) },
        )
        SettingsDialogThemeChooserRow(
            text = "Light",
            selected = settings.darkThemeConfig == LIGHT,
            onClick = { onChangeDarkThemeConfig(LIGHT) },
        )
        SettingsDialogThemeChooserRow(
            text = "Dark",
            selected = settings.darkThemeConfig == DARK,
            onClick = { onChangeDarkThemeConfig(DARK) },
        )
    }
}

@Composable
private fun SettingsDialogSectionTitle(text: String) {
    Text(
        text = text,
        fontFamily = FontFamily(Font(R.font.tiltneon_regular)),
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
    )
}

@Composable
fun SettingsDialogThemeChooserRow(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                role = Role.RadioButton,
                onClick = onClick,
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selected,
            onClick = null,
        )
        Spacer(Modifier.width(8.dp))
        Text(text)
    }
}