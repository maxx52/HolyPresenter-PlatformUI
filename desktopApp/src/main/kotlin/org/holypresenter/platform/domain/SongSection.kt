package org.holypresenter.platform.domain

data class SongSection(
    val type: SongSectionType,
    val number: Int?,
    val lines: List<String>
)