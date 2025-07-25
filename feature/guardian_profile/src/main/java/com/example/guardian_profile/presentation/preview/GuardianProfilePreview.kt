package com.example.guardian_profile.presentation.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.guardian_profile.navigation.UserProfileMode
import com.example.guardian_profile.presentation.GuardianProfileNavigationAction
import com.example.guardian_profile.presentation.GuardianProfileScreen
import com.example.guardian_profile.presentation.GuardianProfileUIState
import com.example.model.enums.Gender

import com.example.model.enums.ScreenState
import com.example.model.guardian.GuardianFullData
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
private val mockActions = object : GuardianProfileNavigationAction{
    override fun navigateUp() {}

    override fun openEmail(email: String) {}

    override fun openContacts(phone: String){}

    override fun navigateToAddChild(guardianId: Int){}

    override fun navigateToChildren(guardianId: Int) {}
}
private val fakeUser = GuardianFullData(
    userId = 1,
    email = "ali.man@gmail.com",
    firstName = "Ali",
    middleName = "Bassam",
    lastName = "Mansoura",
    phoneNumber = "0998439583",
    addressGovernorate = "Syria",
    addressCity = "Latakia",
    addressRegion = "Basnada",
    addressStreet = "Domino Street",
    addressNote = "",
    gender = Gender.MALE.name,
    imgUrl = null
)
@DarkAndLightModePreview
@Composable
fun GuardianProfileSuccessPreview() {
    var uiState by remember{
        mutableStateOf(
        GuardianProfileUIState(
            guardianData = fakeUser,
            userProfileMode = UserProfileMode.VIEW_ONLY,
            screenState = ScreenState.SUCCESS
        )
        )
    }
    Hospital_AutomationTheme {
        GuardianProfileScreen(
            uiState = uiState,
            onAction = {},
            navigationActions = mockActions
        )
    }
}

@DarkAndLightModePreview
@Composable
fun GuardianProfileErrorPreview() {
    var uiState by remember{
        mutableStateOf(
            GuardianProfileUIState(
                guardianData = fakeUser,
                userProfileMode = UserProfileMode.VIEW_ONLY,
                screenState = ScreenState.ERROR
            )
        )
    }
    Hospital_AutomationTheme {
        GuardianProfileScreen(
            uiState = uiState,
            onAction = {},
            navigationActions = mockActions
        )
    }
}
@DarkAndLightModePreview
@Composable
fun GuardianProfileLoadingPreview() {
    var uiState by remember{
        mutableStateOf(
            GuardianProfileUIState(
                guardianData = fakeUser,
                userProfileMode = UserProfileMode.VIEW_ONLY,
                screenState = ScreenState.LOADING
            )
        )
    }
    Hospital_AutomationTheme {
        GuardianProfileScreen(
            uiState = uiState,
            onAction = {},
            navigationActions = mockActions
        )
    }
}
@DarkAndLightModePreview
@Composable
fun GuardianProfileSetAsGuardianPreview() {
    var uiState by remember{
        mutableStateOf(
            GuardianProfileUIState(
                guardianData = fakeUser,
                userProfileMode = UserProfileMode.SET_AS_GUARDIAN,
                childId = 1,
                screenState = ScreenState.SUCCESS
            )
        )
    }
    Hospital_AutomationTheme {
        GuardianProfileScreen(
            uiState = uiState,
            onAction = {},
            navigationActions = mockActions
        )
    }
}
@DarkAndLightModePreview
@Composable
fun GuardianProfileAddChildPreview() {
    var uiState by remember{
        mutableStateOf(
            GuardianProfileUIState(
                guardianData = fakeUser,
                userProfileMode = UserProfileMode.ADD_CHILD,
                screenState = ScreenState.SUCCESS,
                childId = 1
            )
        )
    }
    Hospital_AutomationTheme {
        GuardianProfileScreen(
            uiState = uiState,
            onAction = {},
            navigationActions = mockActions
        )
    }
}