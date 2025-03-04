package cz.mendelu.xzirchuk.project.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.mendelu.xzirchuk.project.R
import cz.mendelu.xzirchuk.project.misc.getAccentColor
import cz.mendelu.xzirchuk.project.misc.getPrimaryColor
import cz.mendelu.xzirchuk.project.ui.screens.TestingConstants
import cz.mendelu.xzirchuk.project.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun MainScreen(
    onIconTap:()->Unit={},
    showTitle:Boolean = true,
    iconImage:ImageVector = Icons.Default.Settings,
    showLoad:Boolean = false,
    placeholder:String?=  null,
    content:@Composable (padding:PaddingValues)->Unit,
){
    Scaffold(
        topBar = {

                CenterAlignedTopAppBar(
                    modifier = Modifier.padding(top = 0.dp, bottom = 12.dp),

                            title = {
                                if(showTitle){
                                    Text(
                                        text = stringResource(R.string.quitra),
                                        fontFamily = accentFont,
                                        color = getAccentColor(),
                                        fontSize = FontSize.header1.size,
                                    )
                                }

                    },
                    navigationIcon = {
                        IconButton(onClick = { onIconTap() }, modifier = Modifier.testTag(TestingConstants.MAIN_SCREEN_BUTTON)) {
                            if(showTitle){
                                Icon(
                                    imageVector = iconImage,
                                    contentDescription = "Localized description",
                                    tint = getAccentColor()
                                )
                            }

                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = getPrimaryColor(),
                        scrolledContainerColor = getPrimaryColor()
                    )
                )

        },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        containerColor = getPrimaryColor()
    ) {
        if(placeholder!= null){
            Column(modifier = Modifier.fillMaxSize()
                , horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(text = placeholder)
            }
        }else
        {
            if(!showLoad){
                content(it)
            }

            else if(showLoad){
                Column(modifier = Modifier.fillMaxSize()
                    , horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp),
                        color = getAccentColor(),
                        trackColor = getAccentColor().copy(alpha = 0.5f),
                    )
                }


            }
        }

        

    }
}


