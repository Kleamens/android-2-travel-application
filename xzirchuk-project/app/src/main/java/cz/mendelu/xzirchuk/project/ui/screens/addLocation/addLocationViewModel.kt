package cz.mendelu.xzirchuk.project.ui.screens.addLocation

import androidx.compose.runtime.mutableStateOf
import cz.mendelu.xzirchuk.project.architecture.BaseViewModel
import cz.mendelu.xzirchuk.project.database.Location
import cz.mendelu.xzirchuk.project.database.LocationsRepoImpl
import cz.mendelu.xzirchuk.project.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddLocationViewModel @Inject constructor(private val repo: LocationsRepoImpl)
    : BaseViewModel(){


    var uiState  = mutableStateOf(
        UiState<String,Int>(
        loading = true,
        data = null,
        errors = null
    )
    )

    fun addLocation(locationLocation: Location){
        launch {
            repo.insertLocation(locationLocation)
        }
    }
//     fun loadLocationName(latLng: LatLng) {
//         launch {
//
//             val result = withContext(Dispatchers.Main) {
//                 apirepo.getReverseGeocode(latLng.latitude, latLng.longitude)
//             }
//             when (result) {
//                 is CommunicationResult.CommunicationError ->
//                     return@launch
//
//                 is CommunicationResult.Error ->
//                     return@launch
//
//                 is CommunicationResult.Exception ->
//                     return@launch
//
//                 is CommunicationResult.Success -> {
//                    uiState.value.data = "${result.data.address?.country}"
//                     uiState.value.loading = false
//                     Log.d("shootme",uiState.value.data.toString())
//                 }
//
//                 else -> {}
//             }
//
//         }
//     }
}