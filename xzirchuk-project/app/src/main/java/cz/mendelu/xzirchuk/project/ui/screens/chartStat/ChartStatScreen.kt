package cz.mendelu.xzirchuk.project.ui.screens.chartStat

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.m3.style.m3ChartStyle
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entriesOf
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.mendelu.xzirchuk.project.R
import cz.mendelu.xzirchuk.project.misc.getAccentColor
import cz.mendelu.xzirchuk.project.misc.getFontColor
import cz.mendelu.xzirchuk.project.ui.UiState
import cz.mendelu.xzirchuk.project.ui.elements.MainScreen
import cz.mendelu.xzirchuk.project.ui.screens.TestingConstants
import cz.mendelu.xzirchuk.project.ui.screens.desinationsList.Nav
import cz.mendelu.xzirchuk.project.ui.screens.destinations.SettingsScreenDestination
import cz.mendelu.xzirchuk.project.ui.theme.FontSize
import cz.mendelu.xzirchuk.project.ui.theme.accentFont

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
@Destination
fun ChartStatScreen(navigator:DestinationsNavigator) {

    val viewModel = hiltViewModel<ChartStatViewModel>()
    var  style = m3ChartStyle(
        getAccentColor()
    )
    val coroutineScope = rememberCoroutineScope()
    val uiState: MutableState<UiState<Map<String,Int>, Int>> =
        rememberSaveable { mutableStateOf(UiState()) }

    var chartEntryModel by remember {
        mutableStateOf( ChartEntryModelProducer())
    }
    LaunchedEffect(key1 = viewModel.uiState, block = {
        Log.d("stat", "start")

        viewModel.loadStats(callback = {
            viewModel.uiState.value.let {
                var instancesList = it.data?.map { it.value }?.toTypedArray()
                chartEntryModel.setEntries(entriesOf(*instancesList!!))
                uiState.value = it

                Log.d("stat", "" )

            }
        })





        Log.d("stat","end")
    })

    val formater = AxisValueFormatter<AxisPosition.Horizontal.Bottom>{value, _ ->
        uiState.value.data?.keys?.toList()?.get(value.toInt())!!
    }


    MainScreen(
        showTitle = true,
        iconImage = Icons.Default.Settings,
        onIconTap = {
                    navigator.navigate(SettingsScreenDestination)
        },
        showLoad =  uiState.value.loading,
        placeholder = uiState.value.errors?.let { stringResource(id = it) }
        ) {
        Column(modifier = Modifier
            .padding(it)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()){
            Text(text = stringResource(R.string.stats), fontSize = FontSize.header1.size, fontFamily = accentFont, color = getFontColor(), modifier = Modifier.testTag(TestingConstants.STAT_SCREEN_TEXT))
            if(uiState.value.data?.isEmpty() == true){
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.65f), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Text(text = stringResource(R.string.no_locations_found), fontSize = FontSize.base.size, color = getFontColor())
                }
            }else{
                Chart(
                    chart = columnChart(
                        listOf(
                            LineComponent(
                                getAccentColor().hashCode(),
                                thicknessDp = 10f,
                                shape = Shapes.roundedCornerShape(8)
                            )
                        )
                    ),
                    modifier = Modifier.fillMaxHeight(0.5f),
                    isZoomEnabled = false,

                    chartModelProducer = chartEntryModel,
                    startAxis = rememberStartAxis(),
                    bottomAxis = rememberBottomAxis(valueFormatter = formater),
                )
                Column(modifier = Modifier.fillMaxWidth()) {
                    uiState.value.data?.forEach {
                        Row(verticalAlignment = Alignment.CenterVertically){
                            Text(
                                text = it.key,
                                fontFamily = accentFont,
                                fontSize = FontSize.header2.size,
                                color = getFontColor()

                            )
                            Divider(modifier =
                            Modifier
                                .fillMaxWidth(0.8f)
                                .padding(horizontal = 8.dp)
                                .background(getFontColor())
                            )
                            Text(
                                text = it.value.toString(),
                                fontFamily = accentFont,
                                fontSize = FontSize.header2.size,
                                color = getFontColor()
                            )
                        }

                    }
                }
            }

            Spacer(modifier = Modifier.fillMaxHeight(0.7f))
            Nav(navigator)

        }

    }


}