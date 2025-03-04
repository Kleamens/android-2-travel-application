package cz.mendelu.xzirchuk.project.ui.screens.addLocation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.PinDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.LatLng
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.mendelu.xzirchuk.project.R
import cz.mendelu.xzirchuk.project.database.Location
import cz.mendelu.xzirchuk.project.misc.getAccentColor
import cz.mendelu.xzirchuk.project.misc.getFontColor
import cz.mendelu.xzirchuk.project.ui.UiState
import cz.mendelu.xzirchuk.project.ui.elements.CustomButton
import cz.mendelu.xzirchuk.project.ui.elements.CustomTextField
import cz.mendelu.xzirchuk.project.ui.elements.MainScreen
import cz.mendelu.xzirchuk.project.ui.theme.FontSize
import cz.mendelu.xzirchuk.project.ui.theme.accentFont

@Composable
@Destination
fun AddLocationScreen(navigator: DestinationsNavigator,latLng: LatLng,name:String){
    val viewModel = hiltViewModel<AddLocationViewModel>()
    val uiState: MutableState<UiState<String, Int>> =
        rememberSaveable {
            mutableStateOf(UiState())
        }



    
    viewModel.uiState.value.let {
        uiState.value = it

    }
    MainScreen(onIconTap = { navigator.popBackStack() },showTitle = false, iconImage = Icons.Default.ArrowBack) {
        Column(
            modifier = Modifier.padding(32.dp)
        ){

                Text(text = stringResource(R.string.add), fontFamily = accentFont, fontSize = FontSize.header0.size, color = getFontColor())
                Row {
                    Icon(
                        Icons.Rounded.PinDrop,
                        contentDescription = "",
                        tint = getFontColor(),
                        modifier = Modifier.size(FontSize.header2.size.value.dp)
                    )
                    Text(text = name , color = getAccentColor(), fontSize = FontSize.header2.size )
                }



            AddLocationScreenContent(viewModel,navigator,latLng,name)



        }
    }
}

@Composable
fun AddLocationScreenContent(viewModel:AddLocationViewModel,navigator: DestinationsNavigator,latLng: LatLng,name:String){
    var locationName by remember {
        mutableStateOf("")
    }
    var locationDescription by remember {
        mutableStateOf("")
    }
    CustomTextField(value = locationName, onValueChange = {locationName = it }, supportingText = stringResource(
        R.string.location_name
    ), isEraseButtonPresent = true, onEraseButtonClick = {
        locationName = ""
    }, modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp))

    CustomTextField(value = locationDescription,
        onValueChange ={locationDescription = it} ,
        supportingText = stringResource(R.string.location_description_optional),
        isEraseButtonPresent = true,
        onEraseButtonClick = {locationDescription = ""},
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f),
        maxLines = 3
        )
    Spacer(modifier = Modifier.fillMaxHeight(0.7f))
    CustomButton(enabled = locationName.isNotBlank(),
        onClick = {
            viewModel.addLocation(Location(name = locationName,
            description = if (locationName.isBlank()) null else locationDescription,
            geocoded_name = name,
            longitude = latLng.longitude,
            latitude = latLng.latitude
        ))
            navigator.popBackStack()
                  }, modifier = Modifier
            .fillMaxWidth()
            .height(48.dp) ){
        Text(text = stringResource(R.string.create))
    }

}

