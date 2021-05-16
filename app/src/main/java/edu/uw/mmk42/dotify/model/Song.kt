package edu.uw.mmk42.dotify.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Song (
    val id: String,
    val title: String,
    val artist: String,


): Parcelable