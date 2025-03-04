package cz.mendelu.xzirchuk.project.ui.elements

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cz.mendelu.xzirchuk.project.misc.getAccentColor
import cz.mendelu.xzirchuk.project.misc.getFontColor
import cz.mendelu.xzirchuk.project.misc.getPrimaryColor
import cz.mendelu.xzirchuk.project.misc.getSecondaryColor

@Composable
fun CustomTextField(value:String,
                    onValueChange:(newValue:String)->Unit,
                    supportingText:String,
                    isEraseButtonPresent:Boolean=false,
                    onEraseButtonClick:()->Unit={},
                    modifier: Modifier = Modifier,
                    maxLines:Int=1,
                    colors:TextFieldColors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = getFontColor(),
                        unfocusedLabelColor = getSecondaryColor(),
                        focusedBorderColor = getAccentColor(),
                        unfocusedBorderColor = getSecondaryColor(),
                        unfocusedTextColor = getFontColor(),
                        focusedTextColor =  getAccentColor(),
                        focusedContainerColor = getPrimaryColor(),
                        unfocusedContainerColor = getPrimaryColor(),



                        ))
{
    OutlinedTextField(
        value = value,
        onValueChange = {onValueChange(it)},
        maxLines = maxLines,
        colors = colors,
        modifier = modifier,
        label = { Text(text = supportingText,color = getPrimaryColor())},
        trailingIcon = {
            if(isEraseButtonPresent){
                if(value.isNotBlank()) {
                    IconButton(onClick = {onEraseButtonClick()}) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            tint = getAccentColor()
                        )
                    }
                }
            }

        }
    )
}