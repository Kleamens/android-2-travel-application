package cz.mendelu.xzirchuk.project.ui.screens.desinationsList

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.xzirchuk.project.R
import cz.mendelu.xzirchuk.project.architecture.BaseViewModel
import cz.mendelu.xzirchuk.project.architecture.CommunicationResult
import cz.mendelu.xzirchuk.project.communication.APIWeatherImpl
import cz.mendelu.xzirchuk.project.communication.ReverseGeocodeRepoImpl
import cz.mendelu.xzirchuk.project.database.Location
import cz.mendelu.xzirchuk.project.database.LocationsRepoImpl
import cz.mendelu.xzirchuk.project.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class DestinationsListViewModel @Inject constructor(private val repo:LocationsRepoImpl,
                                                    private val apirepo:ReverseGeocodeRepoImpl,
                                                    private val weatherrepo:APIWeatherImpl)
    :BaseViewModel(){


    var uiState  = mutableStateOf(UiState<List<Location>,Int>(
        loading = true,
        data = null,
        errors = null
    ))

    init {


        loadLocations()
    }
     fun loadWeather(location: Location, onSucc: (degress:String,location:Location) -> Unit,onFinish:()->Unit ) {

        launch {
            uiState.value = UiState(
                data = uiState.value.data,
                loading = true,
                errors = null
            )
            val result = withContext(Dispatchers.Main) {
                weatherrepo.getWeatherForecast(lat = location.latitude, lon = location.longitude)
            }
            when (result) {
                is CommunicationResult.CommunicationError -> {
                    Log.d("fail", "commerr")
                    uiState.value = UiState(
                        data = uiState.value.data,
                        loading = false,
                        errors = R.string.something_went_wrong
                    )


                    onSucc("?",location)
                }


                is CommunicationResult.Error -> {

                    Log.d("fail", "commerr")
                    uiState.value = UiState(
                        data = uiState.value.data,
                        loading = false,
                        errors = R.string.something_went_wrong
                    )

                    onSucc("?",location)
                }

                is CommunicationResult.Exception -> {
                    Log.d("fail", "commerr")
                    uiState.value = UiState(
                        data = uiState.value.data,
                        loading = false,
                        errors = R.string.something_went_wrong
                    )

                    onSucc("?",location)

                }

                is CommunicationResult.Success -> {
                    Log.d("idie", result.data.current.toString() + Date().time)
//                    showSheet = true

                    uiState.value = UiState(
                        data = uiState.value.data,
                        loading = false,
                        errors = null
                    )


                    onSucc(result.data.current?.temperature_2m?.toInt().toString() ?: "?",location)
                }


            }
        }
        onFinish()




    }

    fun searchByText(text:String){

        launch {
//            uiState.value = UiState(
//                loading = true,
//                data = uiState.value.data,
//                errors = null
//            )
            val regex = Regex(".*${text}.*", RegexOption.IGNORE_CASE)

            var locations = repo.getAll().collect{ locations ->
                var filteredByName = locations.filter {
                    regex.matches(it.name)
                }

                uiState.value = UiState(
                    loading = false,
                    data = filteredByName,
                    errors = null
                )

            }
        }
    }



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
    fun addLocation(){
        launch {
            repo.insertLocation(
                Location(
                name = "test",
                description = "desc",
                geocoded_name = "asldkfj",
                latitude = 1.2,
                longitude = 1.4
            )
            )
        }
    }



}