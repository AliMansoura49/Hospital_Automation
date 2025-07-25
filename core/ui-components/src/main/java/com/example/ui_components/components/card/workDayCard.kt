package com.example.ui_components.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import com.example.constants.icons.AppIcons
import com.example.ext.toAppropriateAddressFormat
import com.example.model.doctor.day_scedule.DaySchedule
import com.example.model.helper.ext.toCapitalized
import com.example.ui.fake.createSampleWorkDayList
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.spacing
import com.example.ui_components.components.icon.IconWithBackground

@Composable
fun WorkDayCard(
    workDay: DaySchedule,
    onItemDeleteClick: (Int?) -> Unit,
    onItemEditClick: (Int?) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(color = MaterialTheme.colorScheme.background)
            .clickable { onItemEditClick(workDay.id) }
            .padding(
                end = MaterialTheme.spacing.extraSmall4,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = workDay.dayOfWeek.name.toCapitalized(),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .weight(0.7f)
                .padding(
                    horizontal = MaterialTheme.spacing.small8
                ),

            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = (workDay.startTime..workDay.endTime).toAppropriateAddressFormat(),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.additionalColorScheme.onBackgroundVariant,
            modifier = Modifier
                .weight(1f)
                .padding(
                    horizontal = MaterialTheme.spacing.extraSmall4
                ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        IconButton(
            onClick = {
                onItemDeleteClick(workDay.id)
            }
        ) {
            IconWithBackground(
                iconRes = AppIcons.Outlined.delete,
                backgroundColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.4f),
                iconColor = MaterialTheme.colorScheme.onErrorContainer,
            )
        }
        IconButton(
            onClick = {
                onItemEditClick(workDay.id)
            }
        ) {
            IconWithBackground(
                iconRes = AppIcons.Outlined.edit,
                backgroundColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.0f),
                iconColor = MaterialTheme.colorScheme.onBackground,
            )
        }


    }
}

@DarkAndLightModePreview
@Composable
fun WorkDayCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            WorkDayCard(
                onItemDeleteClick = {},
                onItemEditClick = {},
                workDay = createSampleWorkDayList()[0],
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
            )
        }
    }
}