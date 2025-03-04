package cz.mendelu.xzirchuk.project.ui.elements

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
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
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.mendelu.xzirchuk.project.R
import cz.mendelu.xzirchuk.project.database.Location
import cz.mendelu.xzirchuk.project.isDarkTheme
import cz.mendelu.xzirchuk.project.misc.CustomClusterRenderer
import cz.mendelu.xzirchuk.project.misc.getAccentColor
import cz.mendelu.xzirchuk.project.misc.getFontColor
import cz.mendelu.xzirchuk.project.misc.getPrimaryColor
import cz.mendelu.xzirchuk.project.misc.getSecondaryColor
import cz.mendelu.xzirchuk.project.ui.UiState
import cz.mendelu.xzirchuk.project.ui.screens.destinations.EditLocationScreenDestination
import cz.mendelu.xzirchuk.project.ui.theme.FontSize
import cz.mendelu.xzirchuk.project.ui.theme.accentFont
import kotlinx.coroutines.launch
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class, MapsComposeExperimentalApi::class)
@Composable
fun CustomBottomSheet(
    sheetState: SheetState,
    onDismiss:()->Unit,
    navigator:DestinationsNavigator,
    degrees:String,
    icon:ImageVector,
    location: cz.mendelu.xzirchuk.project.database.Location,
    ){
    val viewModel = hiltViewModel<CustomBottomSheetViewModel>()
    val formatter = DecimalFormat("#.${"0".repeat(2)}")
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location.position, 10f)
    }
    var degree by remember {
        mutableStateOf(degrees)
    }
    val isDarkTheme by rememberUpdatedState(isDarkTheme.isDarkTheme)
    val uiState: MutableState<UiState<String, Int>> =
        rememberSaveable { mutableStateOf(UiState()) }

    viewModel.uiState.value.let {
        uiState.value = it
    }
    LaunchedEffect(key1 = true, block = {
        viewModel.loadWeather(location, onSucc = {
            degree = it
        })
        Log.d("idie",degree)
    })
    val scope = rememberCoroutineScope()
    val properties by remember {
        mutableStateOf(
            MapProperties(
                mapStyleOptions = if (isDarkTheme) MapStyleOptions(MapStyle.json_dark) else MapStyleOptions(
                    MapStyle.json_light),
                mapType = MapType.NORMAL,
                isTrafficEnabled = false,
                isMyLocationEnabled = false
            )
        )
    }
    val uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                myLocationButtonEnabled = false,
                zoomControlsEnabled = false,
                compassEnabled = false,
                rotationGesturesEnabled = false,
                zoomGesturesEnabled = false,
                scrollGesturesEnabled = false,
                tiltGesturesEnabled = false,
            )
        )
    }
    var showDialog by remember {
        mutableStateOf(false)
    }

    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        sheetState = sheetState,
        containerColor = getPrimaryColor(),
        contentColor = getFontColor(),
        modifier = Modifier
            .fillMaxSize(),

        ) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            AnimatedContent(
                targetState = sheetState.currentValue.name,
                transitionSpec = {
                    fadeIn(
                        animationSpec = tween(250)
                    ) togetherWith fadeOut(animationSpec = tween(20))
                }, label = "")
            { targetState->
                when(targetState){
                    "Expanded"->{
                        IconButton(onClick = {
                            scope.launch {
                                sheetState.show()
                            }
                        }) {
                            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "")
                        }
                    }
                    "PartiallyExpanded" -> {
                        IconButton(onClick = {
                            scope.launch {
                                sheetState.expand()
                            }
                        }) {
                            Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "")
                        }
                    }
                }

            }


        }

        Row(horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()){
            Column(modifier = Modifier.fillMaxWidth(0.4f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center){
                if(uiState.value.loading){
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp),
                        color = getAccentColor(),
                        trackColor = getAccentColor().copy(alpha = 0.5f),
                    )
                }else{
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Text(text = degree, fontSize = FontSize.deg.size, fontFamily = accentFont)
                        Text(text = "°C", fontSize = FontSize.header2.size, fontFamily = accentFont)
                    }
                }


            }
            Column(modifier = Modifier.fillMaxWidth(0.6f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center){
                if (degree!="?" && (degree.toInt())>0){
                    Icon(
                        imageVector =  Icons.Default.WbSunny,
                    contentDescription = "",
                    modifier = Modifier.size(FontSize.deg.size.value.dp)
                    )
                }else{
                    Icon(
                        painter = painterResource(id = R.drawable.winter_fill),
                        contentDescription = "",
                        modifier = Modifier.size(FontSize.deg.size.value.dp)
                    )
                }


            }

        }
        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Top){
            Text(text = location.name, fontSize = FontSize.header1.size, fontFamily = accentFont, modifier = Modifier.padding(vertical = 16.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .background(
                    getSecondaryColor().copy(alpha = 0.5f)
                )
                .padding(vertical = 4.dp, horizontal = 8.dp)
                , horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically)
            {
                Icon(imageVector = Icons.Default.PinDrop, contentDescription = "", tint = getAccentColor(), modifier = Modifier.size(50.dp))
                Column {
                    Row (horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)){
                        Text(text = location.geocoded_name ?: "Unknown",fontSize = FontSize.base.size)
                    }
                    Row (horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Top,modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(horizontal = 8.dp, vertical = 4.dp)){
                        Text(text = formatter.format(location.getPosition().latitude), color = getAccentColor())
                        Spacer(modifier = Modifier.fillMaxWidth(0.2f))
                        Text(text = formatter.format(location.getPosition().longitude),color = getAccentColor())
                    }

                }

            }

        }
        Box(contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
            .padding(vertical = 16.dp)
        )
        {
            GoogleMap(modifier = Modifier
                .fillMaxWidth(0.84f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(8.dp))
                ,
                properties = properties,
                uiSettings = uiSettings,
                cameraPositionState = cameraPositionState
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
                    Clustering(items = listOf(location), clusterRenderer = clusterManager!!.renderer)
                }
            }
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)){
            Text(text = location.description ?:"", modifier = Modifier.padding(vertical = 16.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
                CustomButton(enabled = true, onClick = {
                    showDialog = true
                    /*TODO*/ // todo dialog window wiht confirmatino
                                                       },
                    modifier = Modifier.width(150.dp)) {
                    Text(text = "Delete")
                }
                CustomButton(enabled = true, onClick = { navigator.navigate(EditLocationScreenDestination(id = location.uid!!)) },modifier = Modifier.width(150.dp)) {
                    Text(text = "Edit")
                }
            }

        }
        if(showDialog){
            Dialog(onDismissRequest = { showDialog = false }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(32.dp),
                    shape = RoundedCornerShape(8.dp),

                    ){
                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                        Text(text = "Confirm deletion?", fontSize = FontSize.anotation.size, modifier = Modifier.padding(vertical = 16.dp))

                        Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment =Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()){
                            CustomButton(  onClick = { showDialog = false }) {
                                Text(text = "Cancel")
                            }
                            CustomButton(onClick = {
                                scope.launch {
                                viewModel.deleteLocation(location.uid!!)
                                showDialog = false
                                sheetState.hide()
                                onDismiss()
                                } }) {
                                Text(text = "Delete")
                            }
                        }
                    }
                }
            }
        }















    }

}