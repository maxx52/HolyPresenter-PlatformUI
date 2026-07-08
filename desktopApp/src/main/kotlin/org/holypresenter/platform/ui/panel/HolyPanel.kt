package org.holypresenter.platform.ui.panel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.holypresenter.platform.ui.HolySurface

@Composable
fun HolyPanel(
    title: String,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    HolySurface(
        modifier = modifier
    ) {

        Column(
            Modifier.fillMaxSize()
        ) {

            HolyPanelHeader(
                title = title,
                actions = actions
            )

            HorizontalDivider()

            Spacer(Modifier.height(12.dp))

            content()
        }
    }
}