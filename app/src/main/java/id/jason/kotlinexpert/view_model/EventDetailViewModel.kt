package id.jason.kotlinexpert.view_model

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.jason.kotlinexpert.connection.RetrofitService
import id.jason.kotlinexpert.model.EventsResponse
import id.jason.kotlinexpert.model.TeamsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventDetailViewModel : ViewModel() {
    private var eventDetailData = MutableLiveData<EventsResponse>()
    private var homeTeamData = MutableLiveData<TeamsResponse>()
    private var awayTeamData = MutableLiveData<TeamsResponse>()

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

    internal fun setDataHomeTeam(
        homeId: String,
        context: Context?,
        errorMessage: String
    ) {
        RetrofitService().api().teamDetail(homeId)
            .enqueue(object : Callback<TeamsResponse> {
                override fun onFailure(call: Call<TeamsResponse>, t: Throwable) {
                    homeTeamData.postValue(null)
                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<TeamsResponse>,
                    response: Response<TeamsResponse>
                ) {
                    if (response.isSuccessful) {
                        homeTeamData.postValue(response.body())
                    }
                }
            })

    }

    internal fun getDataHomeTeam(): MutableLiveData<TeamsResponse> {
        return homeTeamData
    }

    internal fun setDataAwayTeam(
        awayId: String,
        context: Context?,
        errorMessage: String
    ) {
        RetrofitService().api().teamDetail(awayId)
            .enqueue(object : Callback<TeamsResponse> {
                override fun onFailure(call: Call<TeamsResponse>, t: Throwable) {
                    awayTeamData.postValue(null)
                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<TeamsResponse>,
                    response: Response<TeamsResponse>
                ) {
                    if (response.isSuccessful) {
                        awayTeamData.postValue(response.body())
                    }
                }
            })
    }

    internal fun getDataAwayTeam(): MutableLiveData<TeamsResponse> {
        return awayTeamData
    }
}