package cz.mendelu.xzirchuk.project.ui.screens.mapScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.google.android.gms.maps.model.LatLng
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.mendelu.xzirchuk.project.R
import cz.mendelu.xzirchuk.project.architecture.BaseViewModel
import cz.mendelu.xzirchuk.project.architecture.CommunicationResult
import cz.mendelu.xzirchuk.project.communication.APIWeatherImpl
import cz.mendelu.xzirchuk.project.communication.ReverseGeocodeRepoImpl
import cz.mendelu.xzirchuk.project.database.Location
import cz.mendelu.xzirchuk.project.database.LocationsRepoImpl
import cz.mendelu.xzirchuk.project.ui.UiState
import cz.mendelu.xzirchuk.project.ui.screens.destinations.AddLocationScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel@Inject constructor(private val repo: LocationsRepoImpl,
                                            private val apirepo: ReverseGeocodeRepoImpl,
                                            private val weatherrepo:APIWeatherImpl
)
    : BaseViewModel() {

    init {
        loadLocations()
    }

    var uiState  = mutableStateOf(
        UiState<List<Location>,Int>(
        loading = true,
        data = null,
        errors = null
    )
    )

    fun loadLocations(){
        launch {
            try {
                repo.getAll().collect(){
                    uiState.value = UiState(
                        loading = false,
                        data = it,
                        errors = null
                    )
                }
            }catch (e : IllegalStateException){
                uiState.value = UiState(
                    loading = false,
                    data = null,
                    errors = R.string.something_went_wrong
                )
            }

        }
    }
    fun loadLocationName(latLng: LatLng,navigator: DestinationsNavigator) {
         launch {
             uiState.value = UiState(
                 loading = true,
                 data = uiState.value.data,
                 errors = null
             )
             try {
                 val result = withContext(Dispatchers.Main) {
                     apirepo.getReverseGeocode(latLng.latitude, latLng.longitude)
                 }
                 when (result) {
                     is CommunicationResult.CommunicationError -> {
                         uiState.value = UiState(
                             loading = false,
                             data = uiState.value.data,
                             errors = null
                         )
                         navigator.navigate(AddLocationScreenDestination(latLng, "Unknown"))
                     }

                     is CommunicationResult.Error -> {
                         uiState.value = UiState(
                             loading = false,
                             data = uiState.value.data,
                             errors = null
                         )
                         navigator.navigate(AddLocationScreenDestination(latLng, "Unknown"))
                     }


                     is CommunicationResult.Exception -> {
                         uiState.value = UiState(
                             loading = false,
                             data = uiState.value.data,
                             errors = null
                         )
                         navigator.navigate(AddLocationScreenDestination(latLng, "Unknown"))
                     }

                     is CommunicationResult.Success -> {
                         var name = "${result.data.address?.country}"
                         uiState.value = UiState(
                             loading = false,
                             data = uiState.value.data,
                             errors = null
                         )
                         navigator.navigate(AddLocationScreenDestination(latLng, name))
                     }

                     else -> {}
                 }
             }catch (e:Exception){
                 uiState.value = UiState(
                     loading = false,
                     data = uiState.value.data,
                     errors = null
                 )
                 navigator.navigate(AddLocationScreenDestination(latLng, "Unknown"))
             }
         }
     }
    suspend fun loadWeather(location: Location): String {


        val result = withContext(Dispatchers.Main) {
            weatherrepo.getWeatherForecast(lat = location.latitude, lon = location.longitude)
        }
        when (result) {
            is CommunicationResult.CommunicationError ->
                return ""

            is CommunicationResult.Error ->
                return ""

            is CommunicationResult.Exception ->
                return ""

            is CommunicationResult.Success -> {
                Log.d("idie",result.data.current.toString() + Date().time)
//                    showSheet = true
                return result.data.current?.temperature_2m?.toInt().toString() ?: "0"
            }


            else -> {return ""}



        }

    }
}