package com.example.hospital_automation.core.components.items

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.hospital_automation.R
import com.example.hospital_automation.core.components.icon.IconWithBackground
import com.example.hospital_automation.ui.helper.DarkAndLightModePreview
import com.example.hospital_automation.ui.theme.Hospital_AutomationTheme
import com.example.hospital_automation.ui.theme.spacing

@Composable
fun ProfileActionsItem(
    @DrawableRes iconRes: Int,
    modifier: Modifier = Modifier,
    iconBackgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f),
    iconColor: Color = MaterialTheme.colorScheme.primary,
    title: String,
    titleColor: Color = MaterialTheme.colorScheme.onBackground,
    showUnderline: Boolean = false,
    onClick: () -> Unit,
) {
    val underlineColor = MaterialTheme.colorScheme.outlineVariant
    val backgroundColor = MaterialTheme.colorScheme.background
    val columnModifier = if (showUnderline) {
        Modifier
            .background(color = backgroundColor)
            .padding(bottom = 1.dp)
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                val y = size.height - strokeWidth / 2
                drawLine(
                    color = underlineColor,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = strokeWidth
                )
            }
    } else {
        modifier
            .background(color = backgroundColor)
            .padding(bottom = 1.dp)
    }

    Column(
        modifier = columnModifier
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium16)
        ) {
            IconWithBackground(
                iconRes = iconRes,
                backgroundColor = iconBackgroundColor,
                iconColor = iconColor,
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium16))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = titleColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun ProfileActionsItemPreview() {
    Hospital_AutomationTheme {
        Surface {
            ProfileActionsItem(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                iconRes = R.drawable.ic_child,
                title = stringResource(R.string.added_children),
                showUnderline = true,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun UnderlinedProfileActionsItemPreview() {
    Hospital_AutomationTheme {
        Surface {
            Column {
                ProfileActionsItem(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    iconRes = R.drawable.ic_child,
                    title = stringResource(R.string.added_children),
                    showUnderline = true,
                )
                ProfileActionsItem(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    iconRes = R.drawable.ic_deactivate_account,
                    title = stringResource(R.string.deactivate_account),
                    showUnderline = false,
                    titleColor = MaterialTheme.colorScheme.error,
                    iconBackgroundColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.4f),
                    iconColor = MaterialTheme.colorScheme.onErrorContainer,
                )
            }

        }
    }
}

