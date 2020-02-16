package id.jason.kotlinexpert.view_model

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.jason.kotlinexpert.connection.RetrofitService
import id.jason.kotlinexpert.model.Event
import id.jason.kotlinexpert.model.EventResponse
import id.jason.kotlinexpert.model.EventsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventSearchViewModel: ViewModel() {
    private var searchEventData = MutableLiveData<EventResponse>()

    internal fun setDataSearchEvent(
        eventQuery: String,
        context: Context?,
        errorMessage: String
    ) {
        RetrofitService().api().searchEvent(eventQuery)
            .enqueue(object : Callback<EventResponse> {
                override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                    searchEventData.postValue(null)
                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<EventResponse>,
                    response: Response<EventResponse>
                ) {
                    if (response.isSuccessful) {
                        searchEventData.postValue(response.body())
                    }
                }
            })
    }

    internal fun getDataSearchEvent(): MutableLiveData<EventResponse> {
        return searchEventData
    }
}