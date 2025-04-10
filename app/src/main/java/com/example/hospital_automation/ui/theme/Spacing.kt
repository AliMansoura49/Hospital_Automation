package com.example.hospital_automation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val default: Dp =0.dp,
    val extraSmall4: Dp =4.dp,
    val small8: Dp =8.dp,
    val small12: Dp =12.dp,
    val medium16: Dp =16.dp,
    val large24: Dp =24.dp,
    val large32: Dp =32.dp,
    val extraLarge64: Dp =64.dp,
)

val LocalSpacing =compositionLocalOf { Spacing() }

val MaterialTheme.spacing
@Composable
@ReadOnlyComposable
get() = LocalSpacing.current