package org.holypresenter.platform.ui.interaction.dragdrop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun <T> rememberHolyDragState(): HolyDragState<T> =
    remember {
        HolyDragState()
    }