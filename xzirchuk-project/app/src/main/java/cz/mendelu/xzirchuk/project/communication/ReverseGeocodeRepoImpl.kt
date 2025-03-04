package cz.mendelu.xzirchuk.project.communication

import cz.mendelu.xzirchuk.project.architecture.CommunicationResult
import cz.mendelu.xzirchuk.project.architecture.IBaseRemoteRepository
import cz.mendelu.xzirchuk.project.models.APILocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

final class ReverseGeocodeRepoImpl
    @Inject constructor(private val reverseGeolocationAPI: ReverseGeolocationAPI)
    :IBaseRemoteRepository,IReverseGeocoding{
    override suspend fun getReverseGeocode(lat:Double,lon:Double): CommunicationResult<APILocation> {
        return processResponse(
            withContext(Dispatchers.IO) {
                reverseGeolocationAPI.getReverseGeocode(lat = lat,lon=lon)
            }
        )
    }
}