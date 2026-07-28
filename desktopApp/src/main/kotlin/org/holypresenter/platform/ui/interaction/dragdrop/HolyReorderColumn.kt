package org.holypresenter.platform.ui.interaction.dragdrop

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

@Composable
fun <T> HolyReorderColumn(
    items: List<T>,
    modifier: Modifier = Modifier,
    onMove: (fromIndex: Int, toIndex: Int) -> Unit,
    itemContent: @Composable ColumnScope.(item: T, index: Int, isDragging: Boolean) -> Unit
) {
    val dragState = rememberHolyDragState<T>()
    var dragOffsetY by remember { mutableStateOf(0f) }
    var itemHeight by remember { mutableStateOf(72f) }

    Column(modifier = modifier) {
        items.forEachIndexed { index, item ->
            val isDragging = dragState.draggingIndex == index

            Column(
                modifier = Modifier
                    .onGloballyPositioned {
                        itemHeight = it.size.height.toFloat()
                    }
                    .offset {
                        if (isDragging) {
                            IntOffset(0, dragOffsetY.roundToInt())
                        } else {
                            IntOffset.Zero
                        }
                    }
                    .pointerInput(items) {
                        detectDragGestures(
                            onDragStart = { offset ->
                                dragOffsetY = 0f
                                dragState.startDrag(item, index, offset)
                            },
                            onDrag = { change, dragAmount ->
                                change.consume()

                                if (dragState.draggingIndex == index) {
                                    dragOffsetY += dragAmount.y
                                    dragState.updatePointer(change.position)

                                    val safeItemHeight = itemHeight.coerceAtLeast(1f)

                                    val targetIndex =
                                        (index + (dragOffsetY / safeItemHeight).roundToInt())
                                            .coerceIn(0, items.lastIndex)

                                    dragState.updateTarget(targetIndex)
                                }
                            },
                            onDragEnd = {
                                dragState.finishDrag()?.let { (from, to) ->
                                    if (from != to) onMove(from, to)
                                }
                                dragOffsetY = 0f
                            },
                            onDragCancel = {
                                dragState.cancel()
                                dragOffsetY = 0f
                            }
                        )
                    }
            ) {
                itemContent(item, index, isDragging)
            }
        }
    }
}