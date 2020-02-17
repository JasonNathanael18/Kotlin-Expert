package id.jason.kotlinexpert.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Event (
    @SerializedName("idEvent") val idEvent: String? = null,
    @SerializedName("strSport") val strSport: String? = null,
    @SerializedName("strEvent") val strEvent: String? = null,
    @SerializedName("strDate") val strDate: String? = null,
    @SerializedName("strHomeTeam") val strHomeTeam: String? = null,
    @SerializedName("strAwayTeam") val strAwayTeam: String? = null,
    @SerializedName("intHomeScore") val intHomeScore: String? = null,
    @SerializedName("intAwayScore") val intAwayScore: String? = null,
    @SerializedName("strHomeGoalDetails") val strHomeGoalDetails: String? = null,
    @SerializedName("strAwayGoalDetails") val strAwayGoalDetails: String? = null,
    @SerializedName("strHomeRedCards") val strHomeRedCards: String? = null,
    @SerializedName("strAwayRedCards") val strAwayRedCards: String? = null,
    @SerializedName("strHomeYellowCards") val strHomeYellowCards: String? = null,
    @SerializedName("strAwayYellowCards") val strAwayYellowCards: String? = null,
    @SerializedName("strHomeLineupGoalkeeper") val strHomeLineupGoalkeeper: String? = null,
    @SerializedName("strHomeLineupDefense") val strHomeLineupDefense: String? = null,
    @SerializedName("strHomeLineupMidfield") val strHomeLineupMidfield: String? = null,
    @SerializedName("strHomeLineupForward") val strHomeLineupForward: String? = null,
    @SerializedName("strHomeLineupSubstitutes") val strHomeLineupSubstitutes: String? = null,
    @SerializedName("strAwayLineupGoalkeeper") val strAwayLineupGoalkeeper: String? = null,
    @SerializedName("strAwayLineupDefense") val strAwayLineupDefense: String? = null,
    @SerializedName("strAwayLineupMidfield") val strAwayLineupMidfield: String? = null,
    @SerializedName("strAwayLineupForward") val strAwayLineupForward: String? = null,
    @SerializedName("strAwayLineupSubstitutes") val strAwayLineupSubstitutes: String? = null
) : Parcelable