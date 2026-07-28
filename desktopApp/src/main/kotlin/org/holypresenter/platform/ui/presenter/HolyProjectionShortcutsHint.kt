package org.holypresenter.platform.ui.presenter

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HolyProjectionShortcutsHint(
    modifier: Modifier = Modifier
) {
    Text(
        text = "← → слайды • B экран • C текст • Esc закрыть",
        modifier = modifier,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}