package com.example.ui_components.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.constants.enums.Gender
import com.example.constants.icons.AppIcons
import com.example.ext.clickableTextRange
import com.example.ext.toAppropriateFormat
import com.example.ext.toCapitalizedString
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.buttons.HospitalAutomationOutLinedButton
import com.example.ui_components.components.items.DetailsItem
import com.example.ui_components.components.items.TagItem
import com.example.ui_components.components.network_image.NetworkImage
import java.time.Instant
import java.util.Date

@Composable
fun ChildProfileCard(
    fatherName:String,
    motherName:String,
    gender: Gender,
    dateOfBirth: Date,
    employeeName: String,
    guardiansNumber: Int,
    onBirthCertificateItemClick: () -> Unit,
    onBirthCertificateItemDescriptionClick: () -> Unit,
    onGuardianTagItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val detailsItemModifier = Modifier
        .fillMaxWidth()
        .padding(
            horizontal = MaterialTheme.spacing.medium16,
            vertical = MaterialTheme.spacing.small12
        )

    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(color = MaterialTheme.colorScheme.background)
            .padding(
                bottom = MaterialTheme.spacing.large24,
                top = MaterialTheme.spacing.small12,
            )
    ) {
        DetailsItem(
            iconRes = AppIcons.Outlined.father,
            title = stringResource(id = R.string.father_name),
            description = fatherName,
            modifier = detailsItemModifier,
        )
        DetailsItem(
            iconRes = AppIcons.Outlined.female,
            title = stringResource(id = R.string.mother_name),
            description = motherName,
            modifier =detailsItemModifier,
        )
        val genderIcon=if(gender==Gender.MALE)AppIcons.Outlined.male else AppIcons.Outlined.female
        DetailsItem(
            iconRes = genderIcon,
            title = stringResource(id = R.string.gender),
            description = "gender".toCapitalizedString(),
            modifier = detailsItemModifier,
        )
        DetailsItem(
            iconRes = AppIcons.Outlined.SpecificDate,
            title = stringResource(id = R.string.date_of_birth),
            description = dateOfBirth.toAppropriateFormat(),
            modifier = detailsItemModifier,
        )
        val birthCertificateDescription=stringResource(R.string.uploaded_by,employeeName)
        DetailsItem(
            iconRes = AppIcons.Outlined.certificate,
            title = stringResource(id = R.string.birth_certificatie),
            description = birthCertificateDescription,
            isClickable = true,
            onClick = onBirthCertificateItemClick,
            modifier = detailsItemModifier,
            isDescriptionClickable = true,
            descriptionClickableTextRange = birthCertificateDescription.clickableTextRange(employeeName),
            onDescriptionClick = onBirthCertificateItemDescriptionClick,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small12))
        TagItem(
            title = stringResource(R.string.guardians),
            description = stringResource(R.string.guardians_number,guardiansNumber),
            onClick = onGuardianTagItemClick,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium16),
        )
    }
}

@DarkAndLightModePreview
@Composable
fun ChildProfileCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            ChildProfileCard(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium16)
                    .fillMaxWidth(),
                onGuardianTagItemClick = {},
                onBirthCertificateItemClick = {},
                onBirthCertificateItemDescriptionClick = {},
                fatherName = "Bassam Mansoura",
                motherName = "Mariam Mansoura",
                employeeName = "Elias Fares",
                dateOfBirth = Date.from(Instant.now()),
                gender = Gender.MALE,
                guardiansNumber = 2,
            )
        }
    }
}