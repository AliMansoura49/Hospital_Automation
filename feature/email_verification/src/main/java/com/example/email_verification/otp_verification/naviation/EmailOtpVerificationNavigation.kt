package com.example.email_verification.otp_verification.naviation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.email_verification.otp_verification.main.OtpVerificationNavigationUiActions
import com.example.email_verification.otp_verification.main.OtpVerificationScreen
import com.example.email_verification.otp_verification.main.OtpVerificationViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class EmailOtpVerificationRoute(
    val email: String,
    val password: String? = null,
    val navigateToResetPassword: Boolean
)

fun NavController.navigateToEmailOtpVerificationScreen(
    email: String,
    password: String? = null,
    navigateToResetPassword: Boolean
) {
    navigateToScreen(EmailOtpVerificationRoute(email,password,navigateToResetPassword))
}

fun NavGraphBuilder.emailOtpVerificationScreen(
    onNavigateToEmailVerifiedSuccessfullyScreen: (email: String, password: String, navigateToResetPassword: Boolean) -> Unit,
) {
    composable<EmailOtpVerificationRoute> {
        val emailOtpVerificationViewModel =
            koinViewModel<OtpVerificationViewModel>()
        val emailOtpVerificationUiState =
            emailOtpVerificationViewModel.uiState.collectAsState()

        val navActions = object : OtpVerificationNavigationUiActions {
            override fun navigateToEmailVerifiedSuccessfullyScreen() {
                onNavigateToEmailVerifiedSuccessfullyScreen(
                    emailOtpVerificationUiState.value.email,
                    emailOtpVerificationUiState.value.password,
                    emailOtpVerificationUiState.value.navigateToResetPassword,
                )
            }
        }

        OtpVerificationScreen(
            uiState = emailOtpVerificationUiState.value,
            uiActions = emailOtpVerificationViewModel.getUiActions(navActions),
        )
    }
}