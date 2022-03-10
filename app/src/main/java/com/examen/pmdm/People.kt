package com.examen.pmdm

import android.os.Parcelable
import com.google.gson.JsonArray
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class People (
    val name: String,
    val height: String,
    val mass: String,

    @SerialName("hair_color")
    val hairColor: String,

    @SerialName("skin_color")
    val skinColor: String,

    @SerialName("eye_color")
    val eyeColor: String,

    @SerialName("birth_year")
    val birthYear: String,

    val gender: String,
    val homeworld: String,
    val films: List<String>,
    val vehicles: List<String>,
    val starships: List<String>,
    val created: String,
    val edited: String,
    val url: String
        ) : Parcelable
