package cz.mendelu.xzirchuk.project.ui.screens.textRecognition

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.mlkittahak.CameraXLivePreviewActivity
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.mendelu.xzirchuk.project.R
import cz.mendelu.xzirchuk.project.misc.getAccentColor
import cz.mendelu.xzirchuk.project.misc.getFontColor
import cz.mendelu.xzirchuk.project.misc.getSecondaryColor
import cz.mendelu.xzirchuk.project.ui.UiState
import cz.mendelu.xzirchuk.project.ui.elements.CustomButton
import cz.mendelu.xzirchuk.project.ui.elements.CustomTextField
import cz.mendelu.xzirchuk.project.ui.elements.MainScreen
import cz.mendelu.xzirchuk.project.ui.screens.TestingConstants
import cz.mendelu.xzirchuk.project.ui.screens.desinationsList.Nav
import cz.mendelu.xzirchuk.project.ui.screens.destinations.SettingsScreenDestination
import cz.mendelu.xzirchuk.project.ui.theme.FontSize
import cz.mendelu.xzirchuk.project.ui.theme.accentFont

@Composable
@Destination
fun TextRecogntionScreen(navigator: DestinationsNavigator) {
    val context = LocalContext.current
    var text by remember {
        mutableStateOf("")
    }

    var defaultText = stringResource(R.string.translation)
    var viewModel by remember {
        mutableStateOf<TextRecognitionViewModel>(TextRecognitionViewModel())
    }
    val uiState: MutableState<UiState<String, Int>> =
        rememberSaveable { mutableStateOf(UiState(loading = false,data =defaultText )) }


    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission Accepted: Do something
            Log.d("ExampleScreen","PERMISSION GRANTED")

        } else {
            // Permission Denied: Do something
           var toast =  Toast.makeText(context, R.string.camera_permission_denied,Toast.LENGTH_SHORT)
            toast.show()
            Log.d("ExampleScreen","PERMISSION DENIED")
        }
    }


    val intent = Intent(context, CameraXLivePreviewActivity::class.java)
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { it ->
            text = it.data?.getStringExtra("result").toString()
//            viewModel.translateText(text, onFinish = { text1 = it })
        })





    MainScreen(showTitle = true,
        showLoad = uiState.value.loading,
        onIconTap = {navigator.navigate((SettingsScreenDestination))},
        placeholder = uiState.value.errors?.let { stringResource(id = it) }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = stringResource(R.string.translate), fontSize = FontSize.header1.size, fontFamily = accentFont, color = getFontColor(), modifier = Modifier.testTag(TestingConstants.TRANSLATE_SCREEN_TEXT))

                Row(modifier = Modifier.fillMaxWidth()) {
                    CustomButton(onClick = {
                        when (PackageManager.PERMISSION_GRANTED) {
                            ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.CAMERA
                            ) -> {

                                launcher.launch(intent)
                            }
                            else -> {
                                // Asking for permission
                                permissionLauncher.launch(Manifest.permission.CAMERA)
                            }
                        }

                    }) {
                        Text(text = stringResource(R.string.take_a_photo))

                    }
                    Spacer(modifier = Modifier.fillMaxWidth(0.5f))

                    CustomButton(onClick = {
//                        uiState.value.loading = true
                        viewModel.translateText(text, onFinish = {
                            viewModel.uiState.value.let { state ->
                            uiState.value = state
                        }})

                    }) {
                        Text(text = stringResource(R.string.translate), modifier = Modifier.testTag(TestingConstants.TRANSLATE_BUTTON))

                    }

                }
                CustomTextField(value = text,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                        .testTag(TestingConstants.TRANSLATE_SCREEN_FIELD),
                    maxLines = 4,
                    onValueChange = { text = it },
                    supportingText = stringResource(R.string.text),
                    isEraseButtonPresent = true,
                    onEraseButtonClick = { text = "" ;uiState.value.data=defaultText})
                Divider(color = getSecondaryColor(), modifier = Modifier.padding(bottom = 16.dp))


                Text(text = uiState.value.data!!, color = getAccentColor(), modifier = Modifier.testTag(TestingConstants.TRANSLATE_SCREEN_RESULT_TEXT))
                
                Spacer(modifier = Modifier.fillMaxHeight(0.85f))
                
                Nav(navigator = navigator)

            }

        }

    }
}



