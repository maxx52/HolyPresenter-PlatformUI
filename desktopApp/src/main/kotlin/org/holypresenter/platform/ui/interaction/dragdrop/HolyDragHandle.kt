package org.holypresenter.platform.ui.interaction.dragdrop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HolyDragHandle(
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier.size(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(3) {
            Text(
                text = "⋮",
                color =
                    if (enabled)
                        MaterialTheme.colorScheme.outline
                    else
                        MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
            )
        }
    }
}