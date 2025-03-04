package cz.mendelu.xzirchuk.project.communication

import cz.mendelu.xzirchuk.project.architecture.CommunicationResult
import cz.mendelu.xzirchuk.project.architecture.IBaseRemoteRepository
import cz.mendelu.xzirchuk.project.models.APITemperature
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

final class APIWeatherImpl
    @Inject constructor(private val weatherAPI: WeatherAPI)
    :IBaseRemoteRepository,IWeatherAPI{
    override suspend fun getWeatherForecast(
        lat: Double,
        lon: Double
    ): CommunicationResult<APITemperature> {
        return processResponse(
            withContext(Dispatchers.IO) {
                weatherAPI.getWeatherForecast(latitude = lat, longitude = lon)
            }
        )
    }

}