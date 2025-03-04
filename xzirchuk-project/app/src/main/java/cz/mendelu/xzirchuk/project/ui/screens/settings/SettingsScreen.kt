package cz.mendelu.xzirchuk.project.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.mendelu.xzirchuk.project.R
import cz.mendelu.xzirchuk.project.isDarkTheme
import cz.mendelu.xzirchuk.project.misc.getAccentColor
import cz.mendelu.xzirchuk.project.misc.getFontColor
import cz.mendelu.xzirchuk.project.ui.UiState
import cz.mendelu.xzirchuk.project.ui.elements.CustomButton
import cz.mendelu.xzirchuk.project.ui.elements.MainScreen
import cz.mendelu.xzirchuk.project.ui.screens.TestingConstants
import cz.mendelu.xzirchuk.project.ui.theme.FontSize
import cz.mendelu.xzirchuk.project.ui.theme.accentFont

@Composable
@Destination
fun SettingsScreen(navigator:DestinationsNavigator){

    var viewModel  by remember{
        mutableStateOf(SettingsViewModel())
    }

    val uiState: MutableState<UiState<Nothing, Int>> =
        rememberSaveable {
            mutableStateOf(UiState())
        }

    var isDark by remember {
        mutableStateOf(isDarkTheme.isDarkTheme)
    }

    viewModel.uiState.value.let {
        uiState.value = it

    }
    var context = LocalContext.current
    MainScreen(showTitle = true, iconImage = Icons.Default.ArrowBack, onIconTap = {navigator.popBackStack()}, showLoad = uiState.value.loading){
        Column(modifier = Modifier
            .padding(it)
            .padding(16.dp)
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
//            Text(text = isDarkTheme.isDarkTheme.toString())
            Text(text = stringResource(R.string.theme), fontSize = FontSize.header1.size, color = getFontColor(), fontFamily =  accentFont, modifier = Modifier.testTag(TestingConstants.SETTINGS_SCREEN_TEXT))


            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                CustomButton(onClick = {
                    isDark =false
                    viewModel.setIsDark(false, context)
                                       }, modifier = Modifier.padding(horizontal = 16.dp).testTag(TestingConstants.SETTINGS_LIGHT_THEME_BUTTON), enabled = isDark, disableColor = if (isDark) getFontColor() else getAccentColor()) {
                    Text(text = "Light")
                }
                CustomButton(onClick = {
                    isDark = true
                    viewModel.setIsDark(true, context)
                                       }, modifier = Modifier.testTag(TestingConstants.SETTINGS_DARK_THEME_BUTTON), enabled = !isDark, disableColor = if (isDark)  getFontColor() else getAccentColor(), activeColor = if (isDark)   getAccentColor()else getFontColor()  ) {
                    Text(text = "Dark")
                }
            }
        }
    }
}