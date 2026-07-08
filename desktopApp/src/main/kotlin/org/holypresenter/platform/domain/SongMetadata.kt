package org.holypresenter.platform.domain

data class SongMetadata(
    val title: String,
    val author: String = "",
    val copyright: String = "",
    val ccli: String = "",
    val tags: List<String> = emptyList(),
    val notes: String = ""
)