package cz.mendelu.xzirchuk.project.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import cz.mendelu.xzirchuk.project.misc.getAccentColor
import cz.mendelu.xzirchuk.project.misc.getFontColor
import cz.mendelu.xzirchuk.project.misc.getSecondaryColor
import cz.mendelu.xzirchuk.project.ui.theme.FontSize
import cz.mendelu.xzirchuk.project.ui.theme.accentFont

@Composable
fun DestinationCard(
    title:String,
    location:String,
    description:String,
    onClick:()->Unit
){
    Box(modifier = Modifier
        // todo potentialy add fillMaxWidth(0.45f) if looks like shit


        .height(150.dp)
        .padding(PaddingValues(8.dp))
        .clip(RoundedCornerShape(8.dp))

        .background(getSecondaryColor())
        .clickable { onClick() }



    ){
        Column(modifier = Modifier.padding(

            PaddingValues(start = 8.dp, top = 8.dp, end = 8.dp, bottom =8.dp)
        )

        ) {
            Text(
                text = if(title.length>10) title.slice(IntRange(0,7))+"..." else title,
                fontFamily = accentFont,
                fontSize = FontSize.header2.size,
                color = getFontColor()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            ){
                Icon(imageVector = Icons.Default.PinDrop,
                    contentDescription = "",
                    modifier = Modifier.size(22.dp),
                    tint = getAccentColor()
                )
                Text(text = location, fontSize = FontSize.anotation.size, color = getFontColor())
            }
            Text(text = if(description.length>10) description.slice(IntRange(0,10))+"..." else description,
                fontSize = FontSize.anotation.size,
                color= getFontColor())


        }
    }
}