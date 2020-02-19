package id.jason.kotlinexpert.view_model

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.jason.kotlinexpert.connection.RetrofitService
import id.jason.kotlinexpert.model.EventsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventViewModel : ViewModel() {
    private var previousMatchData = MutableLiveData<EventsResponse>()
    private var nextMatchData = MutableLiveData<EventsResponse>()

    internal fun setDataPreviousEvent(
        eventId: String,
        context: Context?,
        errorMessage: String
    ) {
        RetrofitService().api().previousMatch(eventId)
            .enqueue(object : Callback<EventsResponse> {
                override fun onFailure(call: Call<EventsResponse>, t: Throwable) {
                    previousMatchData.postValue(null)
                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<EventsResponse>,
                    response: Response<EventsResponse>
                ) {
                    if (response.isSuccessful) {
                        previousMatchData.postValue(response.body())
                    }
                }
            })
    }

    internal fun getDataPreviousEvent(): MutableLiveData<EventsResponse> {
        return previousMatchData
    }

    internal fun setDataNextEvent(
        eventId: String,
        context: Context?,
        errorMessage: String
    ) {
        RetrofitService().api().nextMatch(eventId)
            .enqueue(object : Callback<EventsResponse> {
                override fun onFailure(call: Call<EventsResponse>, t: Throwable) {
                    nextMatchData.postValue(null)
                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<EventsResponse>,
                    response: Response<EventsResponse>
                ) {
                    if (response.isSuccessful) {
                        nextMatchData.postValue(response.body())
                    }
                }
            })
    }

    internal fun getDataNextEvent(): MutableLiveData<EventsResponse> {
        return nextMatchData
    }
}
