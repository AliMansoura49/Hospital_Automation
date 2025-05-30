package com.example.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.constants.enums.Gender
import com.example.constants.icons.AppIcons
import com.example.utility.network.NetworkError
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.buttons.OptionButton
import com.example.ui_components.components.dialog.LoadingDialog
import com.example.ui_components.components.dialog.MessageDialog
import com.example.ui_components.components.list_items.CheckBoxWithDetails
import com.example.ui_components.components.screen_section.SectionWithTitle
import com.example.ui_components.components.text_field.HospitalAutomationTextFiled
import com.example.ui_components.components.topbars.HospitalAutomationTopBarWithOutlinedButton

@Composable
fun SignUpScreen(
    uiState: SignUpUiState,
    uiActions: SignUpUiActions,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(uiState.isSuccessful){
        if (uiState.isSuccessful) {
            uiActions.navigateToEmailVerificationScreen()
        }
    }

    val errorMessage=when(uiState.error){
        is NetworkError->{
            stringResource(R.string.something_went_wrong)
        }
        else -> {
            stringResource(R.string.something_went_wrong)
        }
    }
    MessageDialog(
        showDialog = uiState.showErrorDialog,
        title = stringResource(R.string.signup_error),
        description = errorMessage,
        onConfirm = { uiActions.onShowErrorDialogStateChange(false) },
        confirmButtonText = stringResource(R.string.ok),
        showCancelButton = false,
    )

    LoadingDialog(
        showDialog = uiState.isLoading,
        text = stringResource(R.string.submitting)
    )

    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            HospitalAutomationTopBarWithOutlinedButton(
                title = stringResource(R.string.signup),
                buttonText = stringResource(R.string.login),
                onButtonClick = { uiActions.navigateToLoginScreen() }
            )
        }
    ) { contentPadding ->
        Surface(
            modifier = modifier.padding(contentPadding),
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
            ) {
                Column(
                    modifier = Modifier.padding(
                        start = MaterialTheme.spacing.medium16,
                        end = MaterialTheme.spacing.medium16,
                        top = MaterialTheme.spacing.large24,
                    ),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
                ) {
                    HospitalAutomationTextFiled(
                        value = uiState.firstName,
                        onValueChange = {
                            uiActions.onFirstNameChange(it)
                        },
                        label = R.string.first_name,
                        supportingText = uiState.firstNameError,
                        modifier = Modifier.fillMaxWidth(),
                    )

                    HospitalAutomationTextFiled(
                        value = uiState.middleName,
                        onValueChange = {
                            uiActions.onMiddleNameChange(it)
                        },
                        label = R.string.middle_name,
                        supportingText = uiState.middleNameError,
                        modifier = Modifier.fillMaxWidth(),
                    )

                    HospitalAutomationTextFiled(
                        value = uiState.lastName,
                        onValueChange = {
                            uiActions.onLastNameChange(it)
                        },
                        label = R.string.last_name,
                        supportingText = uiState.lastNameError,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    HospitalAutomationTextFiled(
                        value = uiState.email,
                        onValueChange = {
                            uiActions.onEmailChange(it)
                        },
                        label = R.string.email,
                        supportingText = uiState.emailError,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    HospitalAutomationTextFiled(
                        value = uiState.phoneNumber,
                        onValueChange = {
                            uiActions.onPhoneNumberChange(it)
                        },
                        label = R.string.phone_number,
                        supportingText = uiState.phoneNumberError,
                        modifier = Modifier.fillMaxWidth(),
                    )

                    HospitalAutomationTextFiled(
                        value = uiState.password,
                        onValueChange = {
                            uiActions.onPasswordChange(it)
                        },
                        label = R.string.password,
                        supportingText = uiState.passwordError,
                        modifier = Modifier.fillMaxWidth(),
                    )

                    HospitalAutomationTextFiled(
                        value = uiState.confirmPassword,
                        onValueChange = {
                            uiActions.onConfirmPasswordChange(it)
                        },
                        label = R.string.confirm_password,
                        supportingText = uiState.confirmPasswordError,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))
                Column(
                    modifier = Modifier.padding(
                        start = MaterialTheme.spacing.medium16,
                        end = MaterialTheme.spacing.medium16,
                        bottom = MaterialTheme.spacing.large24,
                    ),
                ) {
                    SectionWithTitle(
                        title = stringResource(R.string.gender)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16),
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            OptionButton(
                                icon = AppIcons.Outlined.male,
                                text = R.string.male,
                                isSelected = uiState.gender == Gender.MALE,
                                onClick = {
                                    uiActions.onGenderChange(Gender.MALE)
                                },
                                modifier = Modifier.weight(1f),
                            )
                            OptionButton(
                                icon = AppIcons.Outlined.female,
                                text = R.string.female,
                                isSelected = uiState.gender == Gender.FEMALE,
                                onClick = {
                                    uiActions.onGenderChange(Gender.FEMALE)
                                },
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large32))

                    CheckBoxWithDetails(
                        checked = uiState.isTermsAndConditionsChecked,
                        onCheckedChange = { uiActions.onTermsAndConditionsCheckChange(it) },
                        title = R.string.I_agree,
                        subTitle = R.string.terms_and_conditions,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large24))

                    CheckBoxWithDetails(
                        checked = uiState.isPersonalDataAccessibilityChecked,
                        onCheckedChange = { uiActions.onPersonalDataAccessibilityCheckChange(it) },
                        title = R.string.I_agree,
                        subTitle = R.string.personal_data_access,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large36))

                    HospitalAutomationButton(
                        onClick = { uiActions.onSignUpButtonClick() },
                        text = stringResource(R.string.signup),
                        modifier = Modifier.fillMaxWidth(),
                    )

                }
            }
        }
    }
}


@DarkAndLightModePreview
@Composable
fun SignUpScreenPreview() {
    Hospital_AutomationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
        ) {
            SignUpScreen(
                uiState = SignUpUiState(),
                uiActions = SignUpUiActions(
                    navigationActions = mockNavigationAction(),
                    businessActions = mockBusinessUiActions(),
                ),
                modifier = Modifier,
            )
        }
    }
}