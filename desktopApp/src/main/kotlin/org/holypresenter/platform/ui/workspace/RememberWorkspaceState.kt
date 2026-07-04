package org.holypresenter.platform.ui.workspace

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberWorkspaceState(
    leftRatio: Float = 0.35f
): WorkspaceState {
    return remember {
        WorkspaceState(
            leftRatio = leftRatio,
            rightRatio = 1f - leftRatio
        )
    }
}