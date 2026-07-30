package org.holypresenter.platform.ui.presenter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HolyProjectionToolbar(
    isBlackScreen: Boolean,
    isTextHidden: Boolean,
    onToggleBlackScreen: () -> Unit,
    onToggleTextVisibility: () -> Unit,
    onCloseProjection: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    showShortcutsHint: Boolean = true
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (showShortcutsHint) {
            HolyProjectionShortcutsHint()
        }

        HolyProjectionControls(
            isBlackScreen = isBlackScreen,
            isTextHidden = isTextHidden,
            enabled = enabled,
            onToggleBlackScreen = onToggleBlackScreen,
            onToggleTextVisibility = onToggleTextVisibility,
            onCloseProjection = onCloseProjection
        )
    }
}