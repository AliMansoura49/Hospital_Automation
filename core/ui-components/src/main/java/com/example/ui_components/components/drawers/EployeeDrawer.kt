package com.example.ui_components.components.drawers

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha.disabled
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.constants.icons.AppIcons
import com.example.ext.disabled
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui_components.R
import com.example.model.DrawerButton
import com.example.ui.theme.spacing

@SuppressLint("UnusedCrossfadeTargetStateParameter")
@Composable
fun EmployeeDrawer(
    state: DrawerState,
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    buttons: List<DrawerButton>,
    selectedIndex: Int?,
    onItemSelected: (index: Int) -> Unit,
    onChangeThemeClick: () -> Unit,
    isDarkTheme: Boolean,
    screen: @Composable () -> Unit,
) {
    ModalNavigationDrawer(
        drawerState = state,
        modifier = modifier,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                modifier = Modifier.fillMaxWidth(0.8f),
            ) {
                Column(
                    modifier = Modifier.padding(MaterialTheme.spacing.small12),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(MaterialTheme.spacing.medium18)
                                .weight(1f),
                            text = stringResource(title),
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        IconButton(
                            onClick = onChangeThemeClick
                        ) {
                            Crossfade(isDarkTheme) {
                                when(it){
                                    true ->  Icon(
                                        painter = painterResource(AppIcons.Outlined.darkMode),
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    false -> Icon(
                                        painter = painterResource(AppIcons.Outlined.lightMode),
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                    buttons.forEachIndexed { currentIndex, item ->
                        NavigationDrawerItem(
                            modifier = Modifier.padding(bottom = MaterialTheme.spacing.small8)
                                then if(!item.enabled) Modifier.disabled() else Modifier
                            ,
                            selected = currentIndex == selectedIndex,
                            onClick = {
                                item.onClick
                                onItemSelected(currentIndex)
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(item.image),
                                    contentDescription = null
                                )
                            },
                            label = {
                                Text(
                                    stringResource(item.text),
                                    style = MaterialTheme.typography.labelLarge,
                                )
                            },
                            colors = NavigationDrawerItemDefaults.colors(
                                unselectedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                unselectedBadgeColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                selectedBadgeColor = MaterialTheme.colorScheme.onPrimaryContainer,

                            ),
                            badge = {
                                item.badgeCount?.let {
                                    Text(
                                        it.toString(),
                                        style = MaterialTheme.typography.labelLarge,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                }
                            }
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = MaterialTheme.spacing.small8),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                }
            }
        },
        gesturesEnabled = state.isOpen ,
        content = screen
    )
}

@DarkAndLightModePreview
@Composable
fun EmployeeDrawerPreview() {
    Hospital_AutomationTheme {
        val buttons = listOf<DrawerButton>(
            DrawerButton(
                text = R.string.profile,
                image = AppIcons.Outlined.accountCircle,
                badgeCount = 24,
                onClick = {}
            ),
            DrawerButton(
                text = R.string.requests,
                image = AppIcons.Outlined.send,
                onClick = {}
            ),
            DrawerButton(
                text = R.string.add_children,
                image = AppIcons.Outlined.notification,
                onClick = {}
            ),
        )
        var selectedIndex by remember { mutableIntStateOf(1) }

        EmployeeDrawer(
            state = remember { DrawerState(initialValue = DrawerValue.Open) },
            title = R.string.medicare,
            buttons = buttons,
            selectedIndex = selectedIndex,
            onItemSelected = {
                selectedIndex = it
            },
            screen = {},
            isDarkTheme = isSystemInDarkTheme(),
            onChangeThemeClick = {},
        )
    }
}