package org.holypresenter.platform.ui.workspace

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HolyWorkspace(
    modifier: Modifier = Modifier,
    sidePaneWidth: Int = 340,
    left: @Composable BoxScope.() -> Unit,
    right: @Composable BoxScope.() -> Unit
) {
    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            left()
        }

        Box(
            modifier = Modifier.width(sidePaneWidth.dp)
        ) {
            right()
        }
    }
}