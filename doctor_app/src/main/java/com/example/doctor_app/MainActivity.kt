package com.example.doctor_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.appointment_details.presentation.AppointmentDetailsScreen
import com.example.appointment_details.presentation.AppointmentDetailsViewModel
import com.example.appointment_details.presentation.AppointmentNavigationActions
import com.example.clinic_details.presentation.ClinicDetailsScreen
import com.example.clinic_details.presentation.ClinicDetailsUIAction
import com.example.clinic_details.presentation.ClinicDetailsViewModel
import com.example.clinic_details.presentation.ClinicNavigationAction
import com.example.clinics_search.presentation.ClinicsSearchNavigationActions
import com.example.clinics_search.presentation.ClinicsSearchScreen
import com.example.clinics_search.presentation.ClinicsSearchViewModel
import com.example.doctor_app.navigation.Navigation

import com.example.doctor_schedule.presentation.DoctorScheduleNavigationActions
import com.example.doctor_schedule.presentation.DoctorScheduleScreen
import com.example.doctor_schedule.presentation.DoctorScheduleViewModel
import com.example.medical_diagnosis.presentation.DiagnosisNavigationActions
import com.example.medical_diagnosis.presentation.DiagnosisScreen
import com.example.medical_diagnosis.presentation.DiagnosisViewModel
import com.example.medicine_details.presentation.MedicineDetailsNavigationActions
import com.example.medicine_details.presentation.MedicineDetailsScreen
import com.example.medicine_details.presentation.MedicineDetailsViewModel
import com.example.medicines_search.presentation.MedicineSearchViewModel
import com.example.medicines_search.presentation.MedicinesSearchNavigationActions
import com.example.medicines_search.presentation.MedicinesSearchScreen
import com.example.medicines_search.presentation.preview.mockNavigation
import com.example.pharmacies.presentaion.PharmaciesNavigationActions
import com.example.pharmacies.presentaion.PharmaciesScreen
import com.example.pharmacies.presentaion.PharmaciesViewModel
import com.example.ui.theme.Hospital_AutomationTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hospital_AutomationTheme {
                val viewModel = koinViewModel<MedicineDetailsViewModel>()
                MedicineDetailsScreen(
                    viewModel = viewModel,
                    navigationActions = object :MedicineDetailsNavigationActions{
                        override fun navigateBack() {
                        }
                    }
                )

            }
        }
    }
}
