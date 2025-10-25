package com.perrystreet.nobigviewmodels.domain.model

import java.util.Date

data class Media(
    val id: String,
    val title: String,
    val imageUrl: String,
    val thumbnailUrl: String,
    val date: Date,
    val type: Type,
    val isPending: Boolean,
) {
    enum class Type {
        Photo,
        Video,
        Gif,
    }
}
