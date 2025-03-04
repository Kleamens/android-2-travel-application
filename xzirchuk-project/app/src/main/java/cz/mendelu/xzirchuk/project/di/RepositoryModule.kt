package cz.mendelu.xzirchuk.project.di

import cz.mendelu.xzirchuk.project.communication.APIWeatherImpl
import cz.mendelu.xzirchuk.project.communication.ReverseGeocodeRepoImpl
import cz.mendelu.xzirchuk.project.communication.ReverseGeolocationAPI
import cz.mendelu.xzirchuk.project.communication.WeatherAPI
import cz.mendelu.xzirchuk.project.database.LocationsDAO
import cz.mendelu.xzirchuk.project.database.LocationsRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLocalPlacesRepository(dao: LocationsDAO): LocationsRepoImpl {
        return LocationsRepoImpl(dao)
    }

    @Provides
    @Singleton
    fun provideGeocodingAPI(geocodingAPI: ReverseGeolocationAPI): ReverseGeocodeRepoImpl
            = ReverseGeocodeRepoImpl(geocodingAPI)
    @Provides
    @Singleton
    fun provideWeatherAPI(weatherAPI: WeatherAPI): APIWeatherImpl
            = APIWeatherImpl(weatherAPI)

}