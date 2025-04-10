package com.example.hospital_automation.core.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.hospital_automation.R
import com.example.hospital_automation.core.components.items.DetailsItem
import com.example.hospital_automation.core.constants.enums.AgeUnit
import com.example.hospital_automation.core.constants.icons.AppIcons
import com.example.hospital_automation.core.ext.toAppropriateFormat
import com.example.hospital_automation.core.model.Age
import com.example.hospital_automation.core.model.Child
import com.example.hospital_automation.ui.helper.DarkAndLightModePreview
import com.example.hospital_automation.ui.theme.Hospital_AutomationTheme
import com.example.hospital_automation.ui.theme.additionalColorScheme
import com.example.hospital_automation.ui.theme.spacing

@Composable
fun ChildCard(
    child: Child,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier
        .clip(shape = MaterialTheme.shapes.small)
        .background(color = MaterialTheme.colorScheme.background)
        .clickable { onClick() }
        .padding(MaterialTheme.spacing.medium16)) {
        Row(modifier=Modifier.fillMaxWidth()) {
            Text(
                text = child.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier=Modifier.width(MaterialTheme.spacing.extraSmall4))
            Text(
                text = child.age.toAppropriateFormat(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.additionalColorScheme.onBackgroundVariantLight,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Spacer(modifier=Modifier.height(MaterialTheme.spacing.large24))
        Row(modifier = Modifier.fillMaxWidth()) {
            DetailsItem(
                title = stringResource(R.string.father),
                description = child.fatherName,
                iconRes = AppIcons.Outlined.father,
                modifier = Modifier.weight(1f),
                isMultipleLines = false,
            )
            DetailsItem(
                title = stringResource(R.string.mother),
                description = child.motherName,
                iconRes = AppIcons.Outlined.mother,
                modifier = Modifier.weight(1f),
                isMultipleLines = false,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun ChildCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            ChildCard(
                child = Child(
                    id = 1,
                    name = "Ziad Mansoura",
                    age = Age(11, AgeUnit.WEEK),
                    fatherName = "Zaid Ahmad",
                    motherName = "Jasmine Ahmad"
                ),
                onClick = {}
            )
        }
    }
}