package cz.mendelu.xzirchuk.project.ui.screens.editLocation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.mendelu.xzirchuk.project.R
import cz.mendelu.xzirchuk.project.database.Location
import cz.mendelu.xzirchuk.project.misc.getFontColor
import cz.mendelu.xzirchuk.project.ui.UiState
import cz.mendelu.xzirchuk.project.ui.elements.CustomButton
import cz.mendelu.xzirchuk.project.ui.elements.CustomTextField
import cz.mendelu.xzirchuk.project.ui.elements.MainScreen
import cz.mendelu.xzirchuk.project.ui.theme.FontSize
import cz.mendelu.xzirchuk.project.ui.theme.accentFont

@Destination
@Composable
fun EditLocationScreen(navigator:DestinationsNavigator,id:Long) {
    val viewModel = hiltViewModel<EditLocationViewModel>()
    val uiState: MutableState<UiState<Location, Int>> =
        rememberSaveable { mutableStateOf(UiState()) }

    viewModel.uiState.value.let {
        uiState.value = it
    }
    var name:String by remember{
        mutableStateOf("")
    }
    var description:String by remember{
        mutableStateOf("")
    }

    LaunchedEffect(key1 = true, block = {
        viewModel.getLocationById(id){ location ->
            name = location.name
            description = location.description ?: ""
        }

    })

    MainScreen(showLoad = uiState.value.loading, placeholder = uiState.value.errors?.let {
        stringResource(id = it)
    }, iconImage = Icons.Default.ArrowBack, onIconTap = {TODO()}) {
        Column(modifier = Modifier
            .padding(it)
            .padding(horizontal = 16.dp)
            .fillMaxSize()) {
            Text(text = stringResource(R.string.edit), fontSize = FontSize.header0.size, fontFamily = accentFont, color = getFontColor())

                CustomTextField(value = name, onValueChange = {
                    name = it
                }, supportingText = stringResource(R.string.name))
                CustomTextField(value = description, onValueChange = {
                    description = it
                }, supportingText = stringResource(R.string.description))
            
                CustomButton(onClick = { viewModel.updateLocation(name = name,description=description, navigator = navigator)}) {
                    Text(text = stringResource(R.string.update))
                }


        }
    }
}