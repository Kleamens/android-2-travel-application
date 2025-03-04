package cz.mendelu.xzirchuk.project.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Location::class], version = 1, exportSchema = false)
abstract class LocationsDatabase : RoomDatabase() {
    abstract fun locationsDao(): LocationsDAO

    companion object {

        private var INSTANCE: LocationsDatabase? = null

        fun getDatabase(context: Context): LocationsDatabase {
            if (INSTANCE == null) {
                synchronized(LocationsDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            LocationsDatabase::class.java, "locations_database"
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}