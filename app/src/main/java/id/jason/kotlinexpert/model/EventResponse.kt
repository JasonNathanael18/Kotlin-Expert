package id.jason.kotlinexpert.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class EventResponse (@SerializedName("event") val events: List<Event>? = arrayListOf()):
    Parcelable