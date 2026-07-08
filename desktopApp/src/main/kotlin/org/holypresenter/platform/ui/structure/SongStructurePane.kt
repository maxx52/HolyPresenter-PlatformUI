package org.holypresenter.platform.ui.structure

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.holypresenter.platform.domain.Song
import org.holypresenter_songs.ui.components.SongSectionCard

@Composable
fun SongStructurePane(
    song: Song?,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxHeight()) {
        Column(Modifier.padding(16.dp)) {
            Text("Структура песни", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(12.dp))

            song?.sections?.forEach { section ->
                SongSectionCard(section)
                Spacer(Modifier.height(12.dp))
            }

            OutlinedButton(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                Text("+ Добавить секцию")
            }
        }
    }
}