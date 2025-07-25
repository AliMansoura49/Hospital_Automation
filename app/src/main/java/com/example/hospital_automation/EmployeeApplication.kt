package com.example.hospital_automation

import android.app.Application
import com.example.add_child_screen.di.addChildModule
import com.example.add_residential_address.addResidentialAddressModule
import com.example.admin_profile.adminProfileModule
import com.example.child_profile.di.childProfileModule
import com.example.children.di.childrenModule
import com.example.children_search.di.childrenSearchModule
import com.example.data.di.employeeAppDataModule
import com.example.email_verification.emailVerificationModule
import com.example.employee_profile.employeeProfileModule
import com.example.employment_history.employmentHistoryModule
import com.example.enter_email.enterEmailModule
import com.example.guardian_profile.di.guardianProfileModule
import com.example.guardians.di.guardiansModule
import com.example.guardians_search.di.guardiansSearchModule
import com.example.home.homeModule
import com.example.hospital_automation.main.appModule
import com.example.login.loginModule
import com.example.reset_password.resetPasswordModule
import com.example.signup.signUpModule
import com.example.upload_child_documents.uploadChildDocumentsModule
import com.example.upload_employee_documents.uploadEmploymentDocumentsModule
import com.example.upload_employee_profile_image.uploadEmployeeProfileImageModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EmployeeApplication: Application() {
    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@EmployeeApplication)
            modules(
                appModule,
                employeeAppDataModule,
                //guardians
                guardiansSearchModule,
                guardianProfileModule,
                guardiansModule,
                //children
                childrenModule,
                addChildModule,
                childProfileModule,
                childrenSearchModule,

                addResidentialAddressModule,
                adminProfileModule,
                emailVerificationModule,
                employeeProfileModule,
                employmentHistoryModule,
                enterEmailModule,
                homeModule,
                loginModule,
                resetPasswordModule,
                signUpModule,
                uploadEmploymentDocumentsModule,
                uploadChildDocumentsModule,
                uploadEmployeeProfileImageModule,
            )
        }
    }
}