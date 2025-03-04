package cz.mendelu.xzirchuk.project.di

import com.squareup.moshi.Moshi
import cz.mendelu.xzirchuk.project.communication.ReverseGeolocationAPI
import cz.mendelu.xzirchuk.project.communication.WeatherAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APIModule {

    @Provides
    @Singleton
    fun providePetsAPI(retrofit: Retrofit): ReverseGeolocationAPI
            = retrofit.create(ReverseGeolocationAPI::class.java)

    @Provides
    @Singleton
    fun provideWeatherAPI(moshi:Moshi):WeatherAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/v1/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val apirepo: WeatherAPI = retrofit.create(WeatherAPI::class.java)

        return apirepo
    }
}