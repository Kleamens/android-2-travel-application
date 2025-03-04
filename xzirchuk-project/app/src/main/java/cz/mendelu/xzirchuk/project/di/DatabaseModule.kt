package cz.mendelu.xzirchuk.project.di

import cz.mendelu.xzirchuk.project.MapsApplication
import cz.mendelu.xzirchuk.project.database.LocationsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(): LocationsDatabase = LocationsDatabase.getDatabase(MapsApplication.appContext)

}