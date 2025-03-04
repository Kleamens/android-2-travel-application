package cz.mendelu.xzirchuk.project.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface LocationsDAO {

    @Query("SELECT * FROM locations")
    fun getAll(): Flow<List<Location>>

    @Query("SELECT * FROM locations WHERE id = :uid")
    suspend fun findLocationById(uid:Long):Location

    @Insert
    suspend fun insertLocation( location: Location)

    @Update
    suspend fun updateLocation(location: Location)

    @Delete
    suspend fun delete(location: Location)
}