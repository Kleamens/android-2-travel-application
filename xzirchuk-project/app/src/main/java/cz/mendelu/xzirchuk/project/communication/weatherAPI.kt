package cz.mendelu.xzirchuk.project.communication

import cz.mendelu.xzirchuk.project.models.APITemperature
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("forecast")
    suspend fun getWeatherForecast(@Query("latitude") latitude:Double,
                                   @Query("longitude") longitude:Double,
                                   @Query("current") hourly:String="temperature_2m",
                                   @Query("forecast_days") forecastDays:Int=1): Response<APITemperature>
}