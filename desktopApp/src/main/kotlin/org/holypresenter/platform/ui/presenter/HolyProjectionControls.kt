package org.holypresenter.platform.ui.presenter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HolyProjectionControls(
    isBlackScreen: Boolean,
    isTextHidden: Boolean,
    onToggleBlackScreen: () -> Unit,
    onToggleTextVisibility: () -> Unit,
    onCloseProjection: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    compact: Boolean = false
) {
    if (compact) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            ProjectionButtons(
                isBlackScreen = isBlackScreen,
                isTextHidden = isTextHidden,
                enabled = enabled,
                onToggleBlackScreen = onToggleBlackScreen,
                onToggleTextVisibility = onToggleTextVisibility,
                onCloseProjection = onCloseProjection
            )
        }
    } else {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ProjectionButtons(
                isBlackScreen = isBlackScreen,
                isTextHidden = isTextHidden,
                enabled = enabled,
                onToggleBlackScreen = onToggleBlackScreen,
                onToggleTextVisibility = onToggleTextVisibility,
                onCloseProjection = onCloseProjection
            )
        }
    }
}

@Composable
private fun ProjectionButtons(
    isBlackScreen: Boolean,
    isTextHidden: Boolean,
    enabled: Boolean,
    onToggleBlackScreen: () -> Unit,
    onToggleTextVisibility: () -> Unit,
    onCloseProjection: () -> Unit
) {
    OutlinedButton(
        enabled = enabled,
        onClick = onToggleBlackScreen
    ) {
        Text(
            text = if (isBlackScreen) {
                    "Вернуть изображение"
                } else {
                    "Чёрный экран"
                },
            maxLines = 1,
            softWrap = false
        )
    }

    OutlinedButton(
        enabled = enabled,
        onClick = onToggleTextVisibility
    ) {
        Text(
            text = if (isTextHidden) {
                "Вернуть текст"
            } else {
                "Скрыть текст"
            },
            maxLines = 1,
            softWrap = false
        )
    }

    OutlinedButton(
        enabled = enabled,
        onClick = onCloseProjection
    ) {
        Text(
            text = "Закрыть проектор",
            maxLines = 1,
            softWrap = false
        )
    }
}