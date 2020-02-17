package id.jason.kotlinexpert.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class TeamsResponse(@SerializedName("teams") val events: List<Team>? = arrayListOf()): Parcelable