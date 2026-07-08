package org.holypresenter.platform.ui.interaction.dragdrop

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HolyDropTarget(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onEntered: () -> Unit = {},
    onExited: () -> Unit = {},
    onDropped: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit
) {
    // Пока это только контейнер.
    // Реальную логику определения наведения мыши
    // добавим после завершения HolyDragState.

    Box(
        modifier = modifier
    ) {
        content()
    }
}