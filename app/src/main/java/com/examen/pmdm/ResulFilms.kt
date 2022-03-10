package com.examen.pmdm

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResulFilms (
    val count: Long,
    val results: List<Film>
):Parcelable