package cz.mendelu.xzirchuk.project.ui.screens.splash

import android.os.Handler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.mendelu.xzirchuk.project.R
import cz.mendelu.xzirchuk.project.misc.getPrimaryColor
import cz.mendelu.xzirchuk.project.ui.screens.destinations.DestinationsListScreenDestination

@Composable
@Destination(start = true)
fun SplashScreeen(navigator: DestinationsNavigator){
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.lottie))
    Handler().postDelayed({
        navigator.popBackStack()
        navigator.navigate(DestinationsListScreenDestination)
    }, 3000)
        Column(modifier = Modifier
            .fillMaxSize()
            .background(getPrimaryColor()), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            LottieAnimation(composition = composition)

        }

}