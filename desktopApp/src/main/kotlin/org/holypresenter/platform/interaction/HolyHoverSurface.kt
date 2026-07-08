package org.holypresenter.platform.interaction

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun HolyHoverSurface(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.(hovered: Boolean) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val hovered by interactionSource.collectIsHoveredAsState()

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(
                if (hovered)
                    MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f)
                else
                    MaterialTheme.colorScheme.surface
            ),
        content = {
            content(hovered)
        }
    )
}