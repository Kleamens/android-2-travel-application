package cz.mendelu.xzirchuk.project.ui.elements

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.xzirchuk.project.architecture.BaseViewModel
import cz.mendelu.xzirchuk.project.architecture.CommunicationResult
import cz.mendelu.xzirchuk.project.communication.APIWeatherImpl
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
class CustomBottomSheetViewModel
@Inject constructor(private val repo: LocationsRepoImpl,
                    private val apirepo: APIWeatherImpl)
    :BaseViewModel(){

    var uiState  = mutableStateOf(
        UiState<String,Int>(
        loading = true,
        data = "?",
        errors = null
    )
    )
    suspend fun deleteLocation(id:Long){

            var location = repo.findLocationById(id)
            repo.delete(
                location
                )


    }

    fun loadWeather(location: Location, onSucc: (degress:String) -> Unit) {

        launch {
            uiState.value = UiState(
                data = uiState.value.data,
                loading = true,
                errors = null
            )

            try {
                val result = withContext(Dispatchers.Main) {
                    apirepo.getWeatherForecast(lat = location.latitude, lon = location.longitude)
                }
                when (result) {
                    is CommunicationResult.CommunicationError -> {
                        Log.d("fail", "commerr")
                        uiState.value = UiState(
                            data = "?",
                            loading = false,
                            errors = null
                        )


                        onSucc("?")
                    }


                    is CommunicationResult.Error -> {

                        Log.d("fail", "commerr")
                        uiState.value = UiState(
                            data = "?",
                            loading = false,
                            errors = null
                        )

                        onSucc("?")
                    }

                    is CommunicationResult.Exception -> {
                        Log.d("fail", "commerr")
                        uiState.value = UiState(
                            data = "?",
                            loading = false,
                            errors = null
                        )

                        onSucc("?")

                    }

                    is CommunicationResult.Success -> {
                        Log.d("idie", result.data.current.toString() + Date().time)
//                    showSheet = true

                        uiState.value = UiState(
                            data = uiState.value.data,
                            loading = false,
                            errors = null
                        )


                        onSucc(result.data.current?.temperature_2m?.toInt().toString() ?: "?")
                    }

                
                }
            }catch (e:Exception){
                uiState.value = UiState(
                    data = uiState.value.data,
                    loading = false,
                    errors = null
                )
            }


        }




    }
//    private fun loadWeather(){
//
//
//        launch {
//
//            val result = withContext(Dispatchers.Main) {
//                apirepo.getWeatherForecast(lat = 1.3170891402794689, lon = 103.83257707590766)
//            }
//            when (result) {
//                is CommunicationResult.CommunicationError ->
//                    return@launch
//
//                is CommunicationResult.Error ->
//                    return@launch
//
//                is CommunicationResult.Exception ->
//                    return@launch
//
//                is CommunicationResult.Success -> {
//                    Log.d("idie",result.data.current.toString())
//                    uiState.value.loading = false
//                }
//
//                else -> {}
//            }
//
//
//        }
//
//    }
}