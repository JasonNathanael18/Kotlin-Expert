package id.jason.kotlinexpert.model.repository

import androidx.lifecycle.LiveData
import id.jason.kotlinexpert.model.Events
import id.jason.kotlinexpert.model.dao.EventDao

class EventRepository(private val eventDao: EventDao) {
    val allFavouriteLastList: LiveData<List<Events>> = eventDao.getFavouriteLastList()

    val allFavouriteNextList: LiveData<List<Events>> = eventDao.getFavouriteNextList()

    suspend fun insert(events: Events) {
        eventDao.insert(events)
    }

    suspend fun deleteFavouriteItem(eventId: String){
        eventDao.deleteFavouriteItem(eventId)
    }
}