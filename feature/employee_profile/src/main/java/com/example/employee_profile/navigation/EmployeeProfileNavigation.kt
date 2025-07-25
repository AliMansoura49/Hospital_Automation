package com.example.employee_profile.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.employee_profile.main.EmployeeProfileNavigationUiActions
import com.example.employee_profile.main.EmployeeProfileScreen
import com.example.employee_profile.main.EmployeeProfileViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data class EmployeeProfileRoute(
    val profileAccessType: ProfileAccessType,
    val employeeId: Int?,
)

fun NavController.navigateToEmployeeProfileScreen(profileAccessType: ProfileAccessType,employeeId: Int?) {
    navigateToScreen(EmployeeProfileRoute(profileAccessType,employeeId))
}

fun NavGraphBuilder.employeeProfileScreen(
    onNavigateToEmploymentHistoryScreen: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    onNavigateUp: () -> Unit,
    onNavigateToAddedChildrenScreen: () -> Unit,
) {
    composable<EmployeeProfileRoute> {
        val viewModel = koinViewModel<EmployeeProfileViewModel>()
        val uiState = viewModel.uiState.collectAsState()

        val navActions = object : EmployeeProfileNavigationUiActions {
            override fun navigateToAddedChildrenScreen() {
                onNavigateToAddedChildrenScreen()
            }

            override fun navigateToEmploymentHistoryScreen() {
                onNavigateToEmploymentHistoryScreen()
            }

            override fun navigateUp() {
                onNavigateUp()
            }

            override fun navigateToLoginScreen() {
                onNavigateToLoginScreen()
            }
        }

        EmployeeProfileScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions)
        )
    }
}
