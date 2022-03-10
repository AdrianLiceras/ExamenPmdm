package com.examen.pmdm

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.json.JsonObject

@Parcelize
data class ResultPeople(val count: Long,
                        val next: String,
                        val results: List<People>):Parcelable

