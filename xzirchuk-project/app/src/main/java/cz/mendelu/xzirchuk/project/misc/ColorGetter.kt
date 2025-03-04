package cz.mendelu.xzirchuk.project.misc

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cz.mendelu.xzirchuk.project.isDarkTheme
import cz.mendelu.xzirchuk.project.ui.theme.AccentDark
import cz.mendelu.xzirchuk.project.ui.theme.AccentLight
import cz.mendelu.xzirchuk.project.ui.theme.FontDark
import cz.mendelu.xzirchuk.project.ui.theme.FontLight
import cz.mendelu.xzirchuk.project.ui.theme.PrimaryDark
import cz.mendelu.xzirchuk.project.ui.theme.PrimaryLight
import cz.mendelu.xzirchuk.project.ui.theme.SecondaryDark
import cz.mendelu.xzirchuk.project.ui.theme.SecondaryLight

@Composable
fun getAccentColor(): Color {
    if (isDarkTheme.isDarkTheme){
        return AccentDark
    }else{
        return AccentLight
    }
}

@Composable
fun getPrimaryColor(): Color {
    return if (isDarkTheme.isDarkTheme){
        PrimaryDark
    }else{
        PrimaryLight
    }
}

@Composable
fun getSecondaryColor(): Color {
    if (isDarkTheme.isDarkTheme){
        return SecondaryDark
    }else{
        return SecondaryLight
    }
}

@Composable
fun getFontColor(): Color {
    if (isDarkTheme.isDarkTheme){
        return FontDark
    }else{
        return FontLight
    }
}

