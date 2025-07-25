package com.example.doctor_app

import android.app.Application
import com.example.add_new_vaccine.addNewVaccineModule
import com.example.add_residential_address.addResidentialAddressModule
import com.example.clinic_details.di.clinicDetailsModule
import com.example.clinics_search.di.clinicsSearchModule
import com.example.data.di.doctorDataModule
import com.example.doctor_app.main.appModule
import com.example.doctor_profile.doctorProfileModule
import com.example.doctor_schedule.di.doctorScheduleModule
import com.example.doctor_signup.doctorSignUpModule
import com.example.email_verification.emailVerificationModule
import com.example.employment_history.employmentHistoryModule
import com.example.enter_email.enterEmailModule
import com.example.login.loginModule
import com.example.reset_password.resetPasswordModule
import com.example.upload_employee_documents.uploadEmploymentDocumentsModule
import com.example.upload_employee_profile_image.uploadEmployeeProfileImageModule
import com.example.vaccine_details_screen.vaccineDetailsModule
import com.example.medical_diagnosis.di.diagnosisModule
import com.example.medicine_details.di.medicineDetailsModule
import com.example.medicines_search.di.medicinesSearchModule
import com.example.pharmacies.di.pharmaciesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DoctorApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DoctorApplication)
            modules(
                medicineDetailsModule,
                doctorDataModule,
                doctorScheduleModule,
                addResidentialAddressModule,
                doctorProfileModule,
                addNewVaccineModule,
                appModule,
                doctorSignUpModule,
                emailVerificationModule,
                loginModule,
                resetPasswordModule,
                enterEmailModule,
                uploadEmployeeProfileImageModule,
                uploadEmploymentDocumentsModule,
                addNewVaccineModule,
                addResidentialAddressModule,
                employmentHistoryModule,
                vaccineDetailsModule,
                clinicsSearchModule,
                clinicDetailsModule,
                medicinesSearchModule,
                pharmaciesModule
            )
        }
    }
}