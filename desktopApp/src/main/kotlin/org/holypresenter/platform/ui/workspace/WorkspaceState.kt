package org.holypresenter.platform.ui.workspace

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class WorkspaceState(
    leftRatio: Float = 0.35f,
    rightRatio: Float = 0.65f
) {
    var leftRatio by mutableStateOf(leftRatio.coerceIn(0.1f, 0.9f))
        private set

    var rightRatio by mutableStateOf(rightRatio.coerceIn(0.1f, 0.9f))
        private set

    var leftVisible by mutableStateOf(true)
        private set

    var rightVisible by mutableStateOf(true)
        private set

    fun updateLeftRatio(value: Float) {
        leftRatio = value.coerceIn(0.1f, 0.9f)
        rightRatio = 1f - leftRatio
    }

    fun showLeft() { leftVisible = true }
    fun hideLeft() { leftVisible = false }
    fun showRight() { rightVisible = true }
    fun hideRight() { rightVisible = false }
}