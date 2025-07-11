package com.example.domain.use_cases.auth.sing_up

import com.example.domain.repositories.auth.singup.BaseSignUpRepository
import com.example.model.auth.signup.BaseRegistrationRequest
import com.example.model.auth.signup.BaseRegistrationResponse
import com.example.utility.network.Result
import com.example.utility.network.rootError

class BaseSignupUseCase(
    private val singUpRepository: BaseSignUpRepository
) {
    suspend operator fun invoke(
        baseRegistrationRequest: BaseRegistrationRequest
    ): Result<BaseRegistrationResponse, rootError> {
        return singUpRepository.signup(baseRegistrationRequest)
    }
}