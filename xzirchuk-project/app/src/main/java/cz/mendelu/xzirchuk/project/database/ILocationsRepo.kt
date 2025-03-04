package cz.mendelu.xzirchuk.project.database

import kotlinx.coroutines.flow.Flow

interface ILocationsRepo {
    fun getAll(): Flow<List<Location>>
    suspend fun findLocationById(uid:Long):Location

    suspend fun insertLocation( location: Location)
    suspend fun updateLocation(location: Location)
    suspend fun delete(location: Location)

}