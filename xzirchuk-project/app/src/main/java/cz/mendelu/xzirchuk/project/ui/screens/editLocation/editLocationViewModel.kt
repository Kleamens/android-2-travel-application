package cz.mendelu.xzirchuk.project.ui.screens.editLocation

import androidx.compose.runtime.mutableStateOf
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.mendelu.xzirchuk.project.R
import cz.mendelu.xzirchuk.project.architecture.BaseViewModel
import cz.mendelu.xzirchuk.project.database.Location
import cz.mendelu.xzirchuk.project.database.LocationsRepoImpl
import cz.mendelu.xzirchuk.project.ui.UiState
import cz.mendelu.xzirchuk.project.ui.screens.destinations.DestinationsListScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class EditLocationViewModel @Inject constructor(private val repo: LocationsRepoImpl):BaseViewModel(){

    var uiState  = mutableStateOf(
        UiState<Location,Int>(
        loading = true,
        data = null,
        errors = null
    )
    )

    fun getLocationById(id:Long,callback:(data:Location)->Unit){
        launch {
            try {
                val location = repo.findLocationById(id)

                uiState.value = UiState(
                    loading = false,
                    data = location,
                    errors = null
                )
                callback(uiState.value.data!!)
            } catch (e:Exception){
                uiState.value = UiState(
                    loading = false,
                    data = null,
                    errors = R.string.something_went_wrong
                )
            }
        }
    }

    fun updateLocation(name:String,description:String,navigator: DestinationsNavigator){



        launch {
            val location = uiState.value.data!!.copy(
                name = name,
                description = description
            )
            repo.updateLocation(location)

            navigator.popBackStack(DestinationsListScreenDestination.route,inclusive = false)
        }
    }
}