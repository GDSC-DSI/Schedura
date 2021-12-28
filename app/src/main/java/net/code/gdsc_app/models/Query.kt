package net.code.gdsc_app.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Query(
    val branch : String,
    val sem : Int,
    val sec : String
) : Parcelable
