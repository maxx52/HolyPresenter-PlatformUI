package org.holypresenter.platform.ui.presenter

import androidx.compose.foundation.layout.Arrangement
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
    enabled: Boolean = true
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedButton(
            enabled = enabled,
            onClick = onToggleBlackScreen
        ) {
            Text(
                if (isBlackScreen) {
                    "Вернуть изображение"
                } else {
                    "Чёрный экран"
                }
            )
        }

        OutlinedButton(
            enabled = enabled,
            onClick = onToggleTextVisibility
        ) {
            Text(
                if (isTextHidden) {
                    "Вернуть текст"
                } else {
                    "Скрыть текст"
                }
            )
        }

        OutlinedButton(
            enabled = enabled,
            onClick = onCloseProjection
        ) {
            Text("Закрыть проектор")
        }
    }
}