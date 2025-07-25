package com.example.ui_components.components.card

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.icon.SystemStateIcon

@Composable
fun PermissionGrantedCard(
    title:String,
    description:String,
    onStartButtonClick:()->Unit,
    modifier: Modifier = Modifier,
) {
    IllustrationCard(
        title = title,
        description = description,
        modifier = modifier,
        illustration = {
            SystemStateIcon(
                iconRes = AppIcons.Outlined.check,
                modifier = Modifier.size(MaterialTheme.sizing.illustrationImageSize)
            )
        },
        actionButtonsSection = {
            HospitalAutomationButton(
                onClick =onStartButtonClick,
                text = stringResource(R.string.start),
                modifier = Modifier.fillMaxWidth()
            )
        }
    )
}

@DarkAndLightModePreview
@Composable
fun PermissionGrantedCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            PermissionGrantedCard(
                title = stringResource(R.string.permission_granted),
                description = stringResource(R.string.employee_permission_granted_description),
                modifier = Modifier.fillMaxWidth(),
                onStartButtonClick = {},
            )
        }
    }
}

