package id.jason.kotlinexpert.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventsResponse(@SerializedName("events") val events: List<Events>? = arrayListOf()):Parcelable