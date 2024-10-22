package com.pe.mascotapp

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val workSansFontFamily = FontFamily(
    Font(resId = R.font.worksans_light, weight = FontWeight.Light),
    Font(resId = R.font.worksans_semibold, weight = FontWeight.SemiBold),
    Font(resId = R.font.worksans_extrabold, weight = FontWeight.ExtraBold),
    Font(resId = R.font.worksans_bold, weight = FontWeight.Bold),
    Font(resId = R.font.worksans_bolditalic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(
        resId = R.font.worksans_extrabolditalic,
        weight = FontWeight.ExtraBold,
        style = FontStyle.Italic
    ),
    Font(resId = R.font.worksans_extralight, weight = FontWeight.ExtraLight),
    Font(
        resId = R.font.worksans_extralightitalic,
        weight = FontWeight.ExtraLight,
        style = FontStyle.Italic
    ),
    Font(resId = R.font.worksans_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(resId = R.font.worksans_lightitalic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(resId = R.font.worksans_medium, weight = FontWeight.Medium),
    Font(
        resId = R.font.worksans_mediumitalic,
        weight = FontWeight.Medium,
        style = FontStyle.Italic
    ),
    Font(resId = R.font.worksans_regular, weight = FontWeight.Normal),
    Font(resId = R.font.worksans_semibold, weight = FontWeight.SemiBold),
    Font(
        resId = R.font.worksans_semibolditalic,
        weight = FontWeight.SemiBold,
        style = FontStyle.Italic
    ),
    Font(resId = R.font.worksans_thin, weight = FontWeight.Thin),
    Font(resId = R.font.worksans_extralight, weight = FontWeight.ExtraLight),
    Font(resId = R.font.worksans_black, weight = FontWeight.Black),
    Font(resId = R.font.worksans_blackitalic, weight = FontWeight.Black, style = FontStyle.Italic),
)

val caprasimoFontFamily = FontFamily(
    Font(R.font.caprasimo_regular, FontWeight.Bold),
)

val bigTitleStyle = TextStyle(
    fontFamily = workSansFontFamily,
    fontSize = 70.sp,
    fontWeight = FontWeight.Bold
)

val caprasimoTitleStyle = TextStyle(
    fontFamily = caprasimoFontFamily,
    fontSize = 24.sp,
    fontWeight = FontWeight.Bold
)

val titleStyle = TextStyle(
    fontFamily = workSansFontFamily,
    fontSize = 20.sp,
    fontWeight = FontWeight.Normal
)

val buttonTitleStyle = TextStyle(
    fontFamily = workSansFontFamily,
    fontSize = 15.sp,
    fontWeight = FontWeight.Normal
)

val boldTitleStyle = TextStyle(
    fontFamily = workSansFontFamily,
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold
)

val semiBoldTitleStyle = TextStyle(
    fontFamily = workSansFontFamily,
    fontSize = 20.sp,
    fontWeight = FontWeight.SemiBold
)


val mediumTitleStyle = TextStyle(
    fontFamily = workSansFontFamily,
    fontSize = 20.sp,
    fontWeight = FontWeight.Medium
)

val descriptionTextStyle = TextStyle(
    fontFamily = workSansFontFamily,
    fontSize = 30.sp,
    fontWeight = FontWeight.Light
)

val textFieldTextStyle = TextStyle(
    fontFamily = workSansFontFamily,
    fontSize = 14.sp,
    fontWeight = FontWeight.Light
)

val chipTextStyle = TextStyle(
    fontFamily = workSansFontFamily,
    fontSize = 11.sp,
    fontWeight = FontWeight.Normal
)



val colorDisabled = Color(0xFFC7C7C7)
val textColor = Color(0xFF4D4D4D)
val colorPrimary = Color(0xFF203E6C)
val colorMediumBlue = Color(0xFF2A6BAF)
val skyBlue = Color(0xFF48A7D3)
val colorHeader = Color(0xFFE0EFF4)
val colorLightGray = Color(0xFFE9E9E9)
val colorYellow = Color(0xFFF2C953)
val colorCyan =Color(0xFFE4EFF3)
val colorHorizontal =Color(0xFFF9F9F9)
val colorGrisTittle = Color(0xFF767676)
val colorDarkGrisTittle = Color(0xFF3F434A)
val colorLightGrisTitle = Color(0xFFF6F6F6)