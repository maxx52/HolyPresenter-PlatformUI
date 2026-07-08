package org.holypresenter.platform.ui.interaction.dragdrop

import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun HolyDraggable(
    modifier: Modifier = Modifier,
    onDragStart: () -> Unit = {},
    onDrag: (dx: Float, dy: Float) -> Unit = { _, _ -> },
    onDragEnd: () -> Unit = {},
    onDragCancel: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier.pointerInput(Unit) {
            detectDragGesturesAfterLongPress(
                onDragStart = { _ ->
                    onDragStart()
                },
                onDrag = { change, dragAmount ->
                    change.consume()
                    onDrag(
                        dragAmount.x,
                        dragAmount.y
                    )
                },
                onDragEnd = {
                    onDragEnd()
                },
                onDragCancel = {
                    onDragCancel()
                }
            )
        }
    ) {
        content()
    }
}