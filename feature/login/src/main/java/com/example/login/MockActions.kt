package com.example.login

import com.example.constants.enums.Gender


fun mockLoginNavigationUiActions()=object : LoginNavigationUiActions {
    override fun navigateToSignUpScreen() {

    }

    override fun navigateToEnterEmailScreen() {
    }

    override fun navigateToHomeScreen() {
    }
}

fun mockLoginBusinessUiActions()=object : LoginBusinessUiActions {
    override fun onEmailChange(email: String) {

    }

    override fun onPasswordChange(password: String) {

    }

    override fun onShowErrorDialogStateChange(value: Boolean) {

    }

    override fun onLoginButtonClick() {

    }

}
