package com.example.ui_components.components.items.custom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ui_components.R
import com.example.ui_components.components.items.ErrorItem
import com.example.constants.icons.AppIcons

@Composable
fun SomeThingWentWrong(
    modifier: Modifier = Modifier
) {
    ErrorItem(
        title = R.string.something_went_wrong,
        subtitle= R.string.no_internet_connection_subtitle,
        icon = AppIcons.Outlined.wifi_off,
        modifier = modifier
    )

}