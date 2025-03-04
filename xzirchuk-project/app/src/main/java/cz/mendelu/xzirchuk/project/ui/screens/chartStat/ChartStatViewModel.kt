package cz.mendelu.xzirchuk.project.ui.screens.chartStat

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.xzirchuk.project.R
import cz.mendelu.xzirchuk.project.architecture.BaseViewModel
import cz.mendelu.xzirchuk.project.database.LocationsRepoImpl
import cz.mendelu.xzirchuk.project.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class ChartStatViewModel
    @Inject constructor(private val repo: LocationsRepoImpl)
    :BaseViewModel(){

    var uiState  = mutableStateOf(
        UiState<Map<String,Int>,Int>(
            loading = true,
            data = null,
            errors = null
        )
    )



    fun loadStats(callback:()->Unit={}){
        launch {
            try {
                repo.getAll().collect(){ it ->
                    val locations = it.groupBy {
                        it.geocoded_name
                    }
                    var returnMap = mutableMapOf<String,Int>()
                    locations.forEach(){
                        returnMap.put(it.key!!,it.value.size)
                        Log.d("stat",it.toString())
                    }

                    uiState.value = UiState(
                        loading = false,
                        data = returnMap,
                        errors =  null
                    )
                    callback()

                }
            }catch (e : IllegalStateException){
                uiState.value = UiState(
                    loading = false,
                    data = null,
                    errors = R.string.something_went_wrong
                )
                callback()
            }
        }
    }
}