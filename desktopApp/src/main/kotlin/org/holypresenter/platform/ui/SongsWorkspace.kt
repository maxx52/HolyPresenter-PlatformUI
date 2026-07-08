package org.holypresenter.platform.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.holypresenter.platform.ui.preview.SongPreviewPane
import org.holypresenter.platform.ui.structure.SongStructurePane
import org.holypresenter.platform.ui.theme.SongThemePane
import org.holypresenter.platform.ui.topbar.SongsTopBar

@Composable
fun SongsWorkspace(
    repository: SongRepository
) {
    val song = repository.getAll().firstOrNull()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SongsTopBar(song = song)

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SongStructurePane(
                song = song,
                modifier = Modifier.weight(0.34f)
            )

            SongPreviewPane(
                song = song,
                modifier = Modifier.weight(0.46f)
            )

            SongThemePane(
                modifier = Modifier.weight(0.20f)
            )
        }
    }
}