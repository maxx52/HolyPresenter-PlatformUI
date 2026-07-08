package org.holypresenter.platform.ui.interaction.dragdrop

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class HolyDragState<T> {
    var draggingItem: T? by mutableStateOf(null)
        private set

    var draggingIndex: Int by mutableStateOf(-1)
        private set

    var targetIndex: Int by mutableStateOf(-1)
        private set

    val isDragging: Boolean
        get() = draggingItem != null

    fun startDrag(
        item: T,
        index: Int
    ) {
        draggingItem = item
        draggingIndex = index
        targetIndex = index
    }

    fun moveTo(index: Int) {
        targetIndex = index
    }

    fun finishDrag(): Pair<Int, Int>? {
        if (!isDragging) return null

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
    }
}