package id.jason.kotlinexpert.connection

import id.jason.kotlinexpert.helper.Constants
import id.jason.kotlinexpert.model.EventResponse
import id.jason.kotlinexpert.model.EventsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APICollections {

    /**
     * Previous Match
     */
    @GET(Constants.ApiEndPoint.PREVIOUS_MATCH)
    fun previousMatch(@Query("id") eventId: String): Call<EventsResponse>

    /**
     * Next Match
     */
    @GET(Constants.ApiEndPoint.NEXT_MATCH)
    fun nextMatch(@Query("id") eventId: String): Call<EventsResponse>

    /**
     * Match Detail
     */
    @GET(Constants.ApiEndPoint.MATCH_DETAIL)
    fun matchDetail(@Query("id") eventId: String): Call<EventsResponse>

    /**
     * Match Detail
     */
    @GET(Constants.ApiEndPoint.SEARCH_EVENT)
    fun searchEvent(@Query("e") eventQuery: String): Call<EventResponse>
}