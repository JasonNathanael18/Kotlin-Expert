package id.jason.kotlinexpert.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.jason.kotlinexpert.model.Events

@Dao
interface EventDao {
    @Query("SELECT * from events_table WHERE intHomeScore IS NOT NULL ORDER BY idEvent DESC")
    fun getFavouriteLastList(): LiveData<List<Events>>

    @Query("SELECT * from events_table WHERE intAwayScore IS NULL ORDER BY idEvent DESC")
    fun getFavouriteNextList(): LiveData<List<Events>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Events)

    @Query("DELETE FROM events_table")
    suspend fun deleteFavouriteAll()

    @Query("DELETE FROM events_table WHERE idEvent = :eventId")
    suspend fun deleteFavouriteItem(eventId: String)
}