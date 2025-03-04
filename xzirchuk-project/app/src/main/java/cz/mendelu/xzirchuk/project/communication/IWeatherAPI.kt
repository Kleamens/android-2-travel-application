package cz.mendelu.xzirchuk.project.communication

import cz.mendelu.xzirchuk.project.architecture.CommunicationResult
import cz.mendelu.xzirchuk.project.architecture.IBaseRemoteRepository
import cz.mendelu.xzirchuk.project.models.APITemperature

interface IWeatherAPI:IBaseRemoteRepository {
    suspend fun getWeatherForecast(lat:Double,lon:Double): CommunicationResult<APITemperature>

}