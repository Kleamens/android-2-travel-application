package cz.mendelu.xzirchuk.project.ui.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cz.mendelu.xzirchuk.project.misc.getAccentColor
import cz.mendelu.xzirchuk.project.misc.getSecondaryColor

@Composable
fun CustomButton(enabled:Boolean = true,onClick:()->Unit,modifier: Modifier=Modifier,activeColor:Color = getAccentColor(),disableColor:Color = getAccentColor(),content:@Composable ()->Unit){
    OutlinedButton(onClick = { onClick() },
        enabled = enabled,
        shape= RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, if (enabled) getAccentColor() else getSecondaryColor().copy(alpha = 0.5f)),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = activeColor.copy(alpha = 0.3f),
            contentColor =  activeColor,
            disabledContainerColor = disableColor.copy(alpha = 0.3f),
            disabledContentColor = disableColor.copy(alpha = 0.5f)
        ),
        modifier = modifier){
        content()
    }
}