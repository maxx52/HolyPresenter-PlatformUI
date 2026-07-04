package org.holypresenter.platform.ui.workspace

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HolySplitPane(
    modifier: Modifier = Modifier,
    ratio: Float = 0.35f,
    left: @Composable () -> Unit,
    right: @Composable () -> Unit
) {
    val safeRatio = ratio.coerceIn(0.1f, 0.9f)

    Row(
        modifier = modifier.fillMaxSize()
    ) {
        HolyPane(
            modifier = Modifier
                .fillMaxHeight()
                .weight(safeRatio)
        ) {
            left()
        }

        HolyPane(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f - safeRatio)
        ) {
            right()
        }
    }
}