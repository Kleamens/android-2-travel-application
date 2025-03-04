package cz.mendelu.xzirchuk.project.communication

import cz.mendelu.xzirchuk.project.models.APILocation
import cz.mendelu.xzirchuk.project.secrets.IQKey
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ReverseGeolocationAPI {
    @GET("reverse")
    suspend fun getReverseGeocode(@Query("lat") lat:Double, @Query("lon") lon:Double, @Query("format") format:String = "json", @Query("key") key:String= IQKey.key): Response<APILocation>

}