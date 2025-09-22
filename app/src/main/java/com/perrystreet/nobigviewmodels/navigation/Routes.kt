package com.perrystreet.nobigviewmodels.navigation

object Routes {
    const val GRID = "grid"
    const val MEDIA_DETAIL = "media_detail"

    fun mediaDetail(mediaId: String) = "$MEDIA_DETAIL/$mediaId"
}
