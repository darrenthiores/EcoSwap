package com.darrenthiores.ecoswap.android.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.darrenthiores.ecoswap.android.R

private val sfProDisplayFont = FontFamily(
    Font(R.font.sf_pro_display_reg),
    Font(R.font.sf_pro_display_medium, weight = FontWeight.SemiBold),
    Font(R.font.sf_pro_display_bold, weight = FontWeight.Bold)
)

// regular
val LargeTitleR = TextStyle(
    fontFamily = sfProDisplayFont,
    fontSize = 34.sp,
    lineHeight = 41.sp
)

val Title1R = TextStyle(
    fontFamily = sfProDisplayFont,
    fontSize = 28.sp,
    lineHeight = 34.sp
)
val Title2R = TextStyle(
    fontFamily = sfProDisplayFont,
    fontSize = 22.sp,
    lineHeight = 28.sp
)
val Title3R = TextStyle(
    fontFamily = sfProDisplayFont,
    fontSize = 20.sp,
    lineHeight = 25.sp
)
val HeadlineR = TextStyle(
    fontFamily = sfProDisplayFont,
    fontWeight = FontWeight.Medium,
    fontSize = 17.sp,
    lineHeight = 22.sp
)
val BodyR = TextStyle(
    fontFamily = sfProDisplayFont,
    fontSize = 17.sp,
    lineHeight = 22.sp
)
val CallOutR = TextStyle(
    fontFamily = sfProDisplayFont,
    fontSize = 16.sp,
    lineHeight = 21.sp
)
val SubHeadlineR = TextStyle(
    fontFamily = sfProDisplayFont,
    fontSize = 15.sp,
    lineHeight = 20.sp
)
val FootnoteR = TextStyle(
    fontFamily = sfProDisplayFont,
    fontSize = 13.sp,
    lineHeight = 18.sp
)
val Caption1R = TextStyle(
    fontFamily = sfProDisplayFont,
    fontSize = 12.sp,
    lineHeight = 16.sp
)
val Caption2R = TextStyle(
    fontFamily = sfProDisplayFont,
    fontSize = 11.sp,
    lineHeight = 13.sp
)

// bold
val LargeTitleB = TextStyle(
    fontFamily = sfProDisplayFont,
    fontWeight = FontWeight.Bold,
    fontSize = 34.sp,
    lineHeight = 41.sp
)

val Title1B = TextStyle(
    fontFamily = sfProDisplayFont,
    fontWeight = FontWeight.Bold,
    fontSize = 28.sp,
    lineHeight = 34.sp
)
val Title2B = TextStyle(
    fontFamily = sfProDisplayFont,
    fontWeight = FontWeight.Bold,
    fontSize = 22.sp,
    lineHeight = 28.sp
)
val Title3B = TextStyle(
    fontFamily = sfProDisplayFont,
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
    lineHeight = 25.sp
)
val HeadlineB = TextStyle(
    fontFamily = sfProDisplayFont,
    fontWeight = FontWeight.Bold,
    fontSize = 17.sp,
    lineHeight = 22.sp
)
val BodyB = TextStyle(
    fontFamily = sfProDisplayFont,
    fontWeight = FontWeight.Bold,
    fontSize = 17.sp,
    lineHeight = 22.sp
)
val CallOutB = TextStyle(
    fontFamily = sfProDisplayFont,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    lineHeight = 21.sp
)
val SubHeadlineB = TextStyle(
    fontFamily = sfProDisplayFont,
    fontWeight = FontWeight.Bold,
    fontSize = 15.sp,
    lineHeight = 20.sp
)
val FootnoteB = TextStyle(
    fontFamily = sfProDisplayFont,
    fontWeight = FontWeight.Bold,
    fontSize = 13.sp,
    lineHeight = 18.sp
)
val Caption1B = TextStyle(
    fontFamily = sfProDisplayFont,
    fontWeight = FontWeight.Bold,
    fontSize = 12.sp,
    lineHeight = 16.sp
)
val Caption2B = TextStyle(
    fontFamily = sfProDisplayFont,
    fontWeight = FontWeight.Bold,
    fontSize = 11.sp,
    lineHeight = 13.sp
)

val Typography = Typography(
    defaultFontFamily = sfProDisplayFont,
    h1 = Title1R,
    h2 = Title2R,
    h3 = Title3R,
    subtitle1 = SubHeadlineB,
    subtitle2 = SubHeadlineR,
    body1 = BodyB,
    body2 = BodyR,
    caption = Caption1R
)