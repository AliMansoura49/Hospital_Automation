package com.example.doctor_schedule.presentation.model

import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import com.example.ext.toAppropriateFormat
import com.example.model.doctor.appointment.AppointmentData
import com.example.ui_components.R
import com.example.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalTime


sealed class AppointmentUIModel {
    class AppointmentModel(
        val id: Int,
        val imageUrl : String,
        val appointmentType: String,
        val fullName: String,
        val date: LocalDate,
        val time : LocalTime,
    ) : AppointmentUIModel(){
        constructor(data: AppointmentData) : this(
            id = data.id,
            appointmentType = data.appointmentType.name,
            imageUrl = data.user?.img?:"",
            fullName = data.user?.fullName?:data.child?.fullName?:"no name",
            date = data.date,
            time = data.time,
        )
    }

    class SeparatorModel(val desc: UiText) : AppointmentUIModel(){
        constructor(appointment: AppointmentData): this(
            desc = UiText.DynamicString(appointment.date.toAppropriateFormat())
        )
    }
}

internal fun Flow<PagingData<AppointmentData>>.upcomingMapper() =
    this.map {pagedData->
        pagedData.map {
            AppointmentUIModel.AppointmentModel(it)
        }
            .insertSeparators { before,after->
                when{
                    //when we got the first
                    before == null -> AppointmentUIModel.SeparatorModel(
                        UiText.StringResource(R.string.today)
                    )
                    after == null -> null
                    before.date.isBefore(after.date)-> {
                        val dateTime = after.date.toAppropriateFormat()
                        AppointmentUIModel.SeparatorModel(
                            UiText.DynamicString(dateTime)
                        )
                    }
                    else -> null
                }
            }
    }
