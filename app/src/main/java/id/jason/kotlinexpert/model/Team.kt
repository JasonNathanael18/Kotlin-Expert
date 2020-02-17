package id.jason.kotlinexpert.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Team(
    @SerializedName("strTeamBadge") val strTeamBadge: String? = null
    ):Parcelable