package com.base.unitconverter.userinterfaceutils.extensions

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.base.unitconverter.ui.theme.LightGrey
import com.base.unitconverter.ui.theme.PrimaryColor

    @Composable
    fun TextStyle.placeholder(): TextStyle {
        return copy(
            fontWeight = FontWeight.Light,
            color = LightGrey
        )
    }

    @Composable
    fun TextStyle.h1(color: Color): TextStyle {
        return copy(
            fontWeight = FontWeight.ExtraBold,
            color = color,
            fontSize = 32.sp
        )
    }


@Composable
fun TextStyle.h2(color: Color): TextStyle {
    return copy(
        fontWeight = FontWeight.SemiBold,
        color = color,
        fontSize = 20.sp
    )
}

@Composable
fun TextStyle.h3(color: Color): TextStyle {
    return copy(
        fontWeight = FontWeight.SemiBold,
        color = color,
        fontSize = 16.sp
    )
}

@Composable
fun TextStyle.h4(color: Color): TextStyle {
    return copy(
        fontWeight = FontWeight.Normal,
        color = color,
        fontSize = 12.sp
    )
}

@Composable
fun String.addColon(): String {
    return "$this :"
}