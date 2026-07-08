package org.holypresenter.platform.ui.interaction.dragdrop

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset

class HolyDragState<T> {
    var draggingItem: T? by mutableStateOf(null)
        private set

    var draggingIndex by mutableStateOf(-1)
        private set

    var targetIndex by mutableStateOf(-1)
        private set

    var pointerPosition by mutableStateOf(Offset.Zero)
        private set

    val isDragging: Boolean
        get() = draggingItem != null

    fun startDrag(
        item: T,
        index: Int,
        pointer: Offset
    ) {
        draggingItem = item
        draggingIndex = index
        targetIndex = index
        pointerPosition = pointer
    }

    fun updatePointer(pointer: Offset) {
        pointerPosition = pointer
    }

    fun updateTarget(index: Int) {
        targetIndex = index
    }

    fun finishDrag(): Pair<Int, Int>? {
        if (!isDragging)
            return null

        val result = draggingIndex to targetIndex

        clear()

        return result
    }

    fun cancel() {
        clear()
    }

    private fun clear() {
        draggingItem = null
        draggingIndex = -1
        targetIndex = -1
        pointerPosition = Offset.Zero
    }
}