package com.example.cobain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListAyat(
    var ayat : String,
    var arti: String
):Parcelable