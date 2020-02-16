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

class EventDetailViewModel: ViewModel() {
    private var eventDetailData = MutableLiveData<EventsResponse>()

    internal fun setDataEventDetail(
        eventId: String,
        context: Context?,
        errorMessage: String
    ) {
        RetrofitService().api().matchDetail(eventId)
            .enqueue(object : Callback<EventsResponse> {
                override fun onFailure(call: Call<EventsResponse>, t: Throwable) {
                    eventDetailData.postValue(null)
                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<EventsResponse>,
                    response: Response<EventsResponse>
                ) {
                    if (response.isSuccessful) {
                        eventDetailData.postValue(response.body())
                    }
                }
            })
    }

    internal fun getDataEventDetail(): MutableLiveData<EventsResponse> {
        return eventDetailData
    }
}