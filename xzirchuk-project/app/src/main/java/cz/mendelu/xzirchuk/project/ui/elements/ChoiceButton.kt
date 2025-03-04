package cz.mendelu.xzirchuk.project.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.mendelu.xzirchuk.project.misc.getAccentColor
import cz.mendelu.xzirchuk.project.ui.theme.FontDark
import cz.mendelu.xzirchuk.project.ui.theme.FontLight

@Composable

fun ToggleButtonExp(options: Array<ToggleButtonOption>,onStateChage:(options:ToggleButtonOption)->Unit) {
    val state = remember { mutableStateMapOf<String, ToggleButtonOption>(Pair<String, ToggleButtonOption>(options.first().text,options.first())) }

    LaunchedEffect(key1 = true ){
        onStateChage(state.values.first())
    }


    ToggleButton(
        state = state,
        options = options,
        type = SelectionType.SINGLE,
        modifier = Modifier.padding(end = 0.dp),
        onClick = { onStateChage(state.values.first()) }
    )
}

enum class SelectionType {
    NONE, SINGLE, MULTIPLE
}

data class ToggleButtonOption(
    val text: String,
)

@Composable
fun SelectionItem(
    option: ToggleButtonOption,
    selected: Boolean,
    shape:RoundedCornerShape,
    onClick: (option: ToggleButtonOption) -> Unit = {}

) {
    OutlinedButton(
        colors = ButtonDefaults.outlinedButtonColors(containerColor = if (selected) getAccentColor().copy(alpha = 0.8f)  else getAccentColor().copy(alpha = 0.3f)
        
        ),
        onClick = { onClick(option) },
        shape = shape,

//        contentPadding = PaddingValues(0.dp),
//        modifier = Modifier.padding(14.dp, 0.dp)
    ) {
        Row(
            modifier = Modifier.padding(0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = option.text,
                color = if (selected) FontLight else FontDark,
                modifier = Modifier.padding(0.dp)
            )

//            if (option.iconRes != null) {
//                Icon(
//                    painter = painterResource(id = option.iconRes),
//                    contentDescription = "Icons",
//                    tint = if (selected) Color.Blue else Color.LightGray,
//                    modifier = Modifier
//                        .padding(4.dp, 2.dp, 2.dp, 2.dp)
//                )
//            }
        }
    }
}

@Composable
fun ToggleButton(
    state:MutableMap<String, ToggleButtonOption>,
    options: Array<ToggleButtonOption>,
    modifier: Modifier = Modifier,
    type: SelectionType = SelectionType.SINGLE,
    onClick: (selectedOptionS: ToggleButtonOption) -> Unit = {}
) {

    Row(
//        onClick = { /*TODO*/ },
//        border = BorderStroke(1.dp, Color.LightGray),
//        shape = RoundedCornerShape(50),
//        colors = ButtonDefaults.outlinedButtonColors(
//            contentColor = Color.LightGray,
//        ),
//        contentPadding = PaddingValues(0.dp, 0.dp),
        modifier = modifier
            .padding(0.dp)
            .height(52.dp)
         ,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
//        if (options.isEmpty()) {
//            return@OutlinedButton
//        }

        val onItemClick: (option: ToggleButtonOption) -> Unit = { option ->
            if (type == SelectionType.SINGLE) {
                options.forEach {
                    val key = it.text
                    if (key == option.text) {
                        state[key] = option
                    } else {
                        state.remove(key)
                    }
                }
            } else {
                val key = option.text
                if (!state.contains(key)) {
                    state[key] = option
                } else {
                    state.remove(key)
                }
            }
        }

        if (options.size == 1) {
            val option = options.first()

            SelectionItem(
                option = option,
                selected = state.contains(option.text),
                shape= RoundedCornerShape(16.dp),
                onClick = {
                    onItemClick(it)
                    onClick(it)
                }
            )
//            return@OutlinedButton
        }

        val first = options.first()
        val last = options.last()
        val middle = options.slice(1..options.size - 2)

        SelectionItem(
            option = first,
            selected = state.contains(first.text),
            shape= RoundedCornerShape(topStart = 16.dp, topEnd = 0.dp, bottomStart = 16.dp, bottomEnd = 0.dp),

            onClick = {
                onItemClick(it)
                onClick(it)
            }
        )


        middle.map { option ->
            SelectionItem(
                option = option,
                selected = state.contains(option.text),
                shape= RoundedCornerShape(0.dp),
                onClick = {
                    onItemClick(it)
                    onClick(it)
                }
            )

        }
        SelectionItem(
            option = last,
            selected = state.contains(last.text),
            shape= RoundedCornerShape(topStart = 0.dp, topEnd = 16.dp, bottomStart = 0.dp, bottomEnd = 16.dp),

            onClick = {
                onItemClick(it)
                onClick(it)
            }
        )
    }
}