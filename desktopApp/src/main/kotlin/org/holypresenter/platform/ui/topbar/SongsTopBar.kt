package org.holypresenter.platform.ui.topbar

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.holypresenter.platform.domain.Song

@Composable
fun SongsTopBar(song: Song?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Songs", style = MaterialTheme.typography.headlineSmall)
        Button(onClick = {}) { Text("Содержание") }
        OutlinedButton(onClick = {}) { Text("Оформление") }

        Spacer(Modifier.weight(1f))

        Text("Название песни\n${song?.metadata?.title.orEmpty()}")
        Text("Автор\n${song?.metadata?.author.orEmpty()}")
    }
}