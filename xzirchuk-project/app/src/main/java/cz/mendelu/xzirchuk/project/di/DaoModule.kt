package cz.mendelu.xzirchuk.project.di

import cz.mendelu.xzirchuk.project.database.LocationsDAO
import cz.mendelu.xzirchuk.project.database.LocationsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideDao(database: LocationsDatabase): LocationsDAO {
        return database.locationsDao()
    }

}