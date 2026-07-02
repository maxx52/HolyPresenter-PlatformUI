package org.holypresenter.platform.ui.workspace

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HolyWorkspace(
    modifier: Modifier = Modifier,
    toolbar: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
    statusBar: @Composable () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            toolbar()
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            content()
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            statusBar()
        }
    }
}