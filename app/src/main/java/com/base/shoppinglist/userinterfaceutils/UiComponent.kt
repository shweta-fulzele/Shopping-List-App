package com.base.shoppinglist.userinterfaceutils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.base.shoppinglist.userinterfaceutils.PlaceholderText
import com.base.shoppinglist.userinterfaceutils.TitleMedium


@Composable
fun MyAppOutlineTextField(
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit = {}
) {
    OutlinedTextField(value = value, onValueChange = onValueChange, placeholder = {
        PlaceholderText(placeholder)
    })
}

@Composable
fun WidthSpacer(width: Int) {
    Spacer(
        modifier = Modifier
            .width(width.dp)
            .fillMaxHeight()
    )
}


@Composable
fun HeightSpacer(height: Int) {
    Spacer(
        modifier = Modifier
            .height(height.dp)
            .fillMaxWidth()
    )
}



