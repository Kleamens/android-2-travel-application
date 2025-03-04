package cz.mendelu.xzirchuk.project.ui.screens.mapScreen



import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.clustering.Clustering
import com.google.maps.android.compose.rememberCameraPositionState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.mendelu.xzirchuk.project.R
import cz.mendelu.xzirchuk.project.database.Location
import cz.mendelu.xzirchuk.project.isDarkTheme
import cz.mendelu.xzirchuk.project.misc.CustomClusterRenderer
import cz.mendelu.xzirchuk.project.misc.getAccentColor
import cz.mendelu.xzirchuk.project.ui.UiState
import cz.mendelu.xzirchuk.project.ui.elements.CustomBottomSheet
import cz.mendelu.xzirchuk.project.ui.elements.MainScreen
import cz.mendelu.xzirchuk.project.ui.elements.MapStyle
import cz.mendelu.xzirchuk.project.ui.elements.ToggleButtonExp
import cz.mendelu.xzirchuk.project.ui.elements.ToggleButtonOption
import cz.mendelu.xzirchuk.project.ui.screens.TestingConstants
import cz.mendelu.xzirchuk.project.ui.screens.desinationsList.Nav
import cz.mendelu.xzirchuk.project.ui.screens.destinations.SettingsScreenDestination
import kotlinx.coroutines.launch

@Composable
@Destination
fun MapScreen(navigator:DestinationsNavigator){
    val viewModel = hiltViewModel<MapScreenViewModel>()
    val uiState: MutableState<UiState<List<Location>, Int>> =
        rememberSaveable {
            mutableStateOf(UiState())
        }

    viewModel.uiState.value.let {
        uiState.value = it

    }
    MainScreen(onIconTap = {
                           navigator.navigate(SettingsScreenDestination)
    }, showLoad = uiState.value.loading, ) {

            MapScreenContent(paddingValues = it, navigator = navigator,items = viewModel.uiState.value.data ?: listOf(),viewModel)
    }

}

@OptIn(MapsComposeExperimentalApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MapScreenContent(paddingValues: PaddingValues,navigator:DestinationsNavigator,items:List<Location>,viewModel: MapScreenViewModel){

    Column(modifier = Modifier.padding(paddingValues)){

        val singapore = LatLng(49.313102888001644, 14.273652550065245)
        var options = arrayOf<ToggleButtonOption>(ToggleButtonOption(stringResource(R.string.view)),ToggleButtonOption(
            stringResource(R.string.add_toggle)))
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(singapore, 10f)
        }
        var curMapState by remember {
            mutableStateOf(options.first())
        }
        var showSheet by remember {
            mutableStateOf(false)
        }
        var selectedLocation:Location? by remember {
            mutableStateOf(null)
        }
        val sheetState = rememberModalBottomSheetState()

        val isDarkTheme by rememberUpdatedState(isDarkTheme.isDarkTheme)
        var degrees by remember {
            mutableStateOf("?")
        }
        var context = LocalContext.current
        val scope = rememberCoroutineScope()
        val permissionLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission Accepted: Do something
                Log.d("ExampleScreen","PERMISSION GRANTED")

            } else {
                // Permission Denied: Do something

                Log.d("ExampleScreen","PERMISSION DENIED")
            }
        }
        LaunchedEffect(key1 = true, block = {
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) -> {


                }
                else -> {
                    // Asking for permission
                    permissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
                }
            }

        })
        val properties by remember {
            mutableStateOf(
                MapProperties(
                    mapStyleOptions = if (isDarkTheme) MapStyleOptions(MapStyle.json_dark) else MapStyleOptions(MapStyle.json_light),
                    mapType = MapType.NORMAL,
                    isTrafficEnabled = false,
                    isMyLocationEnabled = true
                )
            )
        }
        val uiSettings by remember {
            mutableStateOf(
                MapUiSettings(
                    myLocationButtonEnabled = false,
                    zoomControlsEnabled = false,
                    compassEnabled = false
                )
            )
        }
        Box(Modifier.fillMaxSize()) {
            GoogleMap(
                modifier = Modifier
                    .matchParentSize()
                    .testTag(TestingConstants.MAP_SCREEN_MAP),
                cameraPositionState = cameraPositionState,
                properties = properties,
                uiSettings = uiSettings,
                onMapClick = {
                    if(curMapState.text == options[1].text){
                        viewModel.loadLocationName(it,navigator)
                    }

                },



            ) {

                val context = LocalContext.current
                var clusterManager by remember { mutableStateOf<ClusterManager<Location>?>(null) }
                MapEffect(true) { map ->

                    if (clusterManager == null) {
                        clusterManager = ClusterManager<Location>(context, map).apply {
                            renderer = CustomClusterRenderer(context, map, this)

                        }

                    }

                }

                if(clusterManager!= null){

                    Clustering(
                        items = items,
                        clusterItemContent = {
                            Icon(
                                painter = painterResource(id = R.drawable.favorites_fill),
                                tint = getAccentColor(),
                                contentDescription = "")
                        },

//                        clusterRenderer = clusterManager!!.renderer,
                        onClusterItemClick = {
                            if(curMapState.text == options.first().text){
                                scope.launch {
                                    selectedLocation = it
                                    degrees = viewModel.loadWeather(it)
                                    showSheet = true
                                }
                            }

                            true

                        }

                    )
                }




            }
            Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
                ToggleButtonExp(options){
                    curMapState = it

                    //todo from the choice button send the current state and based on that change the state here 
                }

                Nav(navigator = navigator)
            }
            if(showSheet){
                CustomBottomSheet(
                    sheetState = sheetState,
                    onDismiss = { showSheet = false },
                    degrees = degrees,
                    icon = Icons.Default.WbSunny,
                    location = selectedLocation!!,
                    navigator = navigator
                )
            }


        }


    }
}

