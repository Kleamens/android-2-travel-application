@file:Suppress("SpellCheckingInspection")

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import cz.mendelu.pef.petstore.datastore.DataStoreConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreRepositoryImpl(private val context: Context) : IDataStoreRepository {

    override suspend fun setIsDark( isDark:Boolean) {
        val preferencesKey = booleanPreferencesKey(DataStoreConstants.IS_DARK_THEME)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = isDark
        }
    }

    override fun getIsDark(): Flow<Boolean?> {
        val preferencesKey = booleanPreferencesKey(DataStoreConstants.IS_DARK_THEME)
        var isDark = context.dataStore.data.map{ preferences ->
            preferences[preferencesKey]
        }
        return isDark
    }


}