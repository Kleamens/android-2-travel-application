@file:Suppress("DEPRECATION")

package cz.mendelu.xzirchuk.project.ui.screens.desinationsList

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.mendelu.xzirchuk.project.R
import cz.mendelu.xzirchuk.project.SelectedTab
import cz.mendelu.xzirchuk.project.database.Location
import cz.mendelu.xzirchuk.project.isDarkTheme
import cz.mendelu.xzirchuk.project.misc.getAccentColor
import cz.mendelu.xzirchuk.project.misc.getFontColor
import cz.mendelu.xzirchuk.project.misc.getPrimaryColor
import cz.mendelu.xzirchuk.project.misc.getSecondaryColor
import cz.mendelu.xzirchuk.project.ui.Destinations
import cz.mendelu.xzirchuk.project.ui.UiState
import cz.mendelu.xzirchuk.project.ui.elements.CustomBottomSheet
import cz.mendelu.xzirchuk.project.ui.elements.CustomTextField
import cz.mendelu.xzirchuk.project.ui.elements.DestinationCard
import cz.mendelu.xzirchuk.project.ui.elements.MainScreen
import cz.mendelu.xzirchuk.project.ui.screens.TestingConstants
import cz.mendelu.xzirchuk.project.ui.screens.destinations.SettingsScreenDestination
import cz.mendelu.xzirchuk.project.ui.theme.FontSize

@Composable
//@RootNavGraph(start = true) // sets this as the start destination of the default nav graph
@Destination
fun DestinationsListScreen(
    navigator:DestinationsNavigator,
){
    //todo put all the loaded location instances into dataStore so that there wont be extra weather api calls , weather should be updated if lastVisited > 1 hour,or could put all hours and based on time use a library of timezones and get the needed thing
    // todo add a try catch where catch triggers a error in uiState else idk
    val viewModel = hiltViewModel<DestinationsListViewModel>()
    val uiState: MutableState<UiState<List<Location>, Int>> =
        rememberSaveable { mutableStateOf(UiState()) }

    viewModel.uiState.value.let {
        uiState.value = it
    }

    MainScreen(onIconTap = { navigator.navigate(SettingsScreenDestination) }, showLoad = uiState.value.loading, placeholder = uiState.value.errors?.let { stringResource(id = it)}) {
        if(uiState.value.loading){
            //todo loader
            Text("loading")
        }else{
            DestinationsListContent(paddingValues = it,navigator=navigator,viewModel = viewModel,uiState)
        }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationsListContent(
    paddingValues: PaddingValues,
    navigator: DestinationsNavigator,
    viewModel:DestinationsListViewModel,
    uiState:MutableState<UiState<List<Location>,Int>>
){
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    var context = LocalContext.current
    val scope = rememberCoroutineScope()

    val isDarkTheme by rememberUpdatedState(isDarkTheme.isDarkTheme)

    var selectedLocation:Location? by remember {
        mutableStateOf(null)
    }
    var degrees by remember {
        mutableStateOf("?")
    }
    var searchText by remember{
        mutableStateOf("")
    }




    Column(modifier = Modifier
        .padding(paddingValues)
        .padding(horizontal = 8.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally){
        CustomTextField(value = searchText, onValueChange ={
            searchText = it
            viewModel.searchByText(searchText)
        },
            supportingText = stringResource(R.string.search) ,
            maxLines = 1, modifier = Modifier
                .testTag(TestingConstants.SEARCH_FIELD)
                .fillMaxWidth(0.9f), isEraseButtonPresent = true, onEraseButtonClick = {
                searchText = ""
                viewModel.searchByText(searchText)

            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = getPrimaryColor(),
                unfocusedBorderColor = getPrimaryColor(),
                unfocusedTextColor = getAccentColor(),
                focusedTextColor =  getFontColor(),
                focusedLabelColor = getFontColor(),
                focusedContainerColor = getSecondaryColor(),
                unfocusedContainerColor = getSecondaryColor()
            ))
//        Button(onClick = {
//            Log.d("test", cz.mendelu.xzirchuk.project.isDarkTheme.isDarkTheme.toString())
//
//            cz.mendelu.xzirchuk.project.isDarkTheme.isDarkTheme = false
//            Log.d("test", cz.mendelu.xzirchuk.project.isDarkTheme.isDarkTheme.toString())
//        }) {
////            Text("Add some shit")
//        }
//        Text(text = isSystemInDarkTheme().toString())
        if(uiState.value.data?.isEmpty() == true){
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.856f)
            , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text(text = stringResource(id = R.string.no_locations_found), color = getFontColor(), fontSize = FontSize.base.size)
            }
        }else{
            LazyVerticalGrid(modifier = Modifier

                .fillMaxWidth()
                .fillMaxHeight(0.856f)


                ,
                userScrollEnabled= true,
                horizontalArrangement = Arrangement.Center,
                columns = GridCells.Adaptive(minSize = 135.dp),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                content ={
                    viewModel.uiState.value.data?.forEach {
                        item {
                            DestinationCard(
                                title = it.name,
                                location = it.geocoded_name ?: "idk",
                                description = it.description ?: "idk",
                                onClick = {


                                    selectedLocation = it
                                    showBottomSheet = true


                                    Log.d("idie",selectedLocation.toString())

                                }
                            )
                        }
                    }

                })
        }

        Nav(navigator = navigator)

    }
    if (showBottomSheet){
                CustomBottomSheet(
                    sheetState = sheetState,
                    onDismiss = { showBottomSheet = false },

                    degrees = degrees,
                    icon = Icons.Default.WbSunny,
                    location = selectedLocation!!,
                    navigator = navigator
                )

            }
        }




@Composable
fun Nav(navigator:DestinationsNavigator){

    val items = arrayListOf<Destinations>(
        Destinations.DestinationsList(),
        Destinations.Camera(),
        Destinations.Map(),
        Destinations.Dashboard()
    )


    val nav = NavigationBar(modifier = Modifier.fillMaxWidth(), containerColor = getPrimaryColor(),) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                       when (item){
                           is Destinations.Camera -> {
                               Icon(Icons.Default.CameraAlt,contentDescription = "")
                           }
                           is Destinations.Dashboard ->{
                               Icon(Icons.Default.Analytics,contentDescription = "")

                           }
                           is Destinations.DestinationsList -> {
                               Icon(Icons.Default.List,contentDescription = "",)

                           }
                           is Destinations.Map ->{
                               Icon(Icons.Default.Map,contentDescription = "" )
                           }
                       }
                },
                selected = SelectedTab.tab.route == item.route,
                onClick = {
                    if(SelectedTab.tab == item){

                    }else{
                        SelectedTab.tab = item
                        navigator.popBackStack()
                        navigator.navigate(direction = item.route)

                    }


                }
                ,  colors = androidx.compose.material3.NavigationBarItemDefaults
                    .colors(
                        selectedIconColor = getAccentColor(),
                        unselectedIconColor = getFontColor(),
                        indicatorColor = Color.Transparent
                    )
                , modifier = when(item){
                    is Destinations.Camera ->Modifier.testTag(TestingConstants.TRANSLATE_NAV_BUTTON)
                    is Destinations.Dashboard -> Modifier.testTag(TestingConstants.STAT_NAV_BUTTON)
                    is Destinations.DestinationsList ->Modifier.testTag(TestingConstants.LIST_NAV_BUTTON)
                    is Destinations.Map -> Modifier.testTag(TestingConstants.MAP_NAV_BUTTON)
                }
            )
        }
    }
}



