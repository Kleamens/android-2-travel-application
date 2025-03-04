package cz.mendelu.xzirchuk.project.database

import kotlinx.coroutines.flow.Flow

class LocationsRepoImpl(private val dao:LocationsDAO):ILocationsRepo{
    override fun getAll(): Flow<List<Location>> {
        return dao.getAll()
    }

    override suspend fun findLocationById(uid: Long):Location{
        return dao.findLocationById(uid)
    }

    override suspend fun insertLocation(location: Location) {
        dao.insertLocation(location)
    }

    override suspend fun updateLocation(location: Location) {
        dao.updateLocation(location)
    }

    override suspend fun delete(location: Location) {
        dao.delete(location)
    }
}