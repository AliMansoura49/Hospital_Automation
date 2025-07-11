package com.example.domain.di.medicine

import com.example.domain.use_cases.medicine.GetMedicinesFlowUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val medicinesUseCaseModule = module {
    singleOf(::GetMedicinesFlowUseCase)
}