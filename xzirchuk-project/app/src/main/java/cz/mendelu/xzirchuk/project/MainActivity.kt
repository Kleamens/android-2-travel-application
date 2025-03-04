package cz.mendelu.xzirchuk.project

import DataStoreRepositoryImpl
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.ramcosta.composedestinations.DestinationsNavHost
import cz.mendelu.pef.petstore.datastore.DataStoreConstants
import cz.mendelu.xzirchuk.project.ui.Destinations
import cz.mendelu.xzirchuk.project.ui.screens.NavGraphs
import cz.mendelu.xzirchuk.project.ui.theme.ProjectTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            val coroutine = rememberCoroutineScope()
            var context = applicationContext
            LaunchedEffect(key1 = true, block = {
              setIsDarkObject(context)

            })
            ProjectTheme {

                Column (modifier = Modifier.fillMaxSize()){
                    isDarkTheme.isDarkTheme = isSystemInDarkTheme()

                    DestinationsNavHost(navGraph = NavGraphs.root )
                }
            }
        }

    }


}

object SelectedTab{
    var tab: Destinations = Destinations.DestinationsList()
}
object RecognizedText{
    var text:String = "Nothing"
}
object isDarkTheme{
    var isDarkTheme:Boolean = false
}
suspend fun setIsDarkObject(context: Context){

        var dataStore = DataStoreRepositoryImpl(context)
        val preferencesKey = booleanPreferencesKey(DataStoreConstants.IS_DARK_THEME)

        dataStore.getIsDark().collect {
            if (it == null) {
                dataStore.setIsDark(isDarkTheme.isDarkTheme)
            } else {
                isDarkTheme.isDarkTheme = it
            }
            Log.d("test", it.toString())

        }



}