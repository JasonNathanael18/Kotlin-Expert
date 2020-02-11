package id.jason.kotlinexpert.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    var leagueName: String,
    var leagueId: String,
    var leagueDescription: String,
    var leagueBadge: Int
):Parcelable