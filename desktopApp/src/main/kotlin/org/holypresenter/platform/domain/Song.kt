package org.holypresenter.platform.domain

data class Song(
    val id: SongId,
    val metadata: SongMetadata,
    val sections: List<SongSection> = emptyList(),
    val theme: SongTheme? = null
)