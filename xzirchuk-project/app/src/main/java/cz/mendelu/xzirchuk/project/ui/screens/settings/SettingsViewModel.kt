package cz.mendelu.xzirchuk.project.ui.screens.settings

import DataStoreRepositoryImpl
import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.xzirchuk.project.architecture.BaseViewModel
import cz.mendelu.xzirchuk.project.isDarkTheme
import cz.mendelu.xzirchuk.project.ui.UiState
import kotlinx.coroutines.launch


class SettingsViewModel  :BaseViewModel() {

    var uiState = mutableStateOf(
        UiState<Nothing, Int>(
            loading = false,
            data = null,
            errors = null
        )
    )

    fun setIsDark(isDark: Boolean, context: Context) {

        launch {

            uiState.value = UiState(
                loading = true,
                data = null,
                errors = null
            )
            Log.d("test", isDarkTheme.isDarkTheme.toString())



            isDarkTheme.isDarkTheme = isDark
            var dataStore = DataStoreRepositoryImpl(context)
            dataStore.setIsDark(isDark)
            uiState.value = UiState(
                loading = false,
                data = null,
                errors = null
            )

        }
    }
}
