package cz.mendelu.xzirchuk.project.communication

import cz.mendelu.xzirchuk.project.architecture.CommunicationResult
import cz.mendelu.xzirchuk.project.architecture.IBaseRemoteRepository
import cz.mendelu.xzirchuk.project.models.APILocation

interface IReverseGeocoding : IBaseRemoteRepository {

    suspend fun getReverseGeocode(lat:Double,lon:Double): CommunicationResult<APILocation>

}