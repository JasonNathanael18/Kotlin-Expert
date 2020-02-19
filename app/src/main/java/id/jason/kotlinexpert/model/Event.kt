package id.jason.kotlinexpert.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Event (
    @SerializedName("idEvent") val idEvent: String? = null,
    @SerializedName("idHomeTeam") val idHomeTeam: String? = null,
    @SerializedName("idAwayTeam") val idAwayTeam: String? = null,
    @SerializedName("strSport") val strSport: String? = null,
    @SerializedName("strEvent") val strEvent: String? = null,
    @SerializedName("strDate") val strDate: String? = null,
    @SerializedName("strHomeTeam") val strHomeTeam: String? = null,
    @SerializedName("strAwayTeam") val strAwayTeam: String? = null,
    @SerializedName("intHomeScore") val intHomeScore: String? = null,
    @SerializedName("intAwayScore") val intAwayScore: String? = null
) : Parcelable