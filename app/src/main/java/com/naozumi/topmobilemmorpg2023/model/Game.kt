package com.naozumi.topmobilemmorpg2023.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val name: String,
    val icon: Int,
    val publisher: String,
    val rating: String,
    val reviewCount: String,
    val description: String,
    val totalDownloads: Int,
    val minimumOS: String,
    val releasedOn: String,
    val lastUpdated: String
): Parcelable
