package com.examen.pmdm

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
    data class Film (
        val title: String,

        @SerialName("episode_id")
        val episodeID: Long,

        @SerialName("opening_crawl")
        val openingCrawl: String,

        val director: String,
        val producer: String,

        @SerialName("release_date")
        val releaseDate: String,

        val characters: List<String>,
        val planets: List<String>,
        val starships: List<String>,
        val vehicles: List<String>,
        val species: List<String>,
        val created: String,
        val edited: String,
        val url: String
    ):Parcelable

