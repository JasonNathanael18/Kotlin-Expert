package id.jason.kotlinexpert.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import id.jason.kotlinexpert.database.EventDatabase
import id.jason.kotlinexpert.model.Events
import id.jason.kotlinexpert.model.repository.EventRepository
import kotlinx.coroutines.launch

class FavouriteViewModel(application: Application) : AndroidViewModel(application) {
    private val eventRepository: EventRepository
    // LiveData gives us updated words when they change.
    val allLastEvent: LiveData<List<Events>>
    val allNextEvent: LiveData<List<Events>>

    init {
        val eventDao = EventDatabase.getDatabase(application).eventDao()
        eventRepository = EventRepository(eventDao)
        allLastEvent = eventRepository.allFavouriteLastList
        allNextEvent = eventRepository.allFavouriteLastList
    }

    fun insert(events: Events) = viewModelScope.launch {
        eventRepository.insert(events)
    }

    fun deleteShowById(eventId: String) = viewModelScope.launch {
        eventRepository.deleteFavouriteItem(eventId)
    }
}