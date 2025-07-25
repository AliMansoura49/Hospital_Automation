package com.example.data.mapper.child

import com.example.model.child.ChildData
import com.example.model.child.ChildFullData
import com.example.network.model.dto.child.ChildDto
import com.example.network.model.dto.child.ChildFullDto
import com.example.network.model.request.child.AddChildRequest
import com.example.network.model.response.child.AddChildResponse
import com.example.network.model.response.child.ChildFullResponse
import com.example.network.utility.ApiRoutes

internal fun ChildDto.toChildData() =
    ChildData(
        id =childId ,
        firstName = firstName,
        lastName = lastName,
        fatherFirstName = fatherFirstName,
        fatherLastName = fatherLastName,
        motherFirstName = motherFirstName,
        motherLastName =motherLastName,
        dateOfBirth = dateOfBirth
    )

internal fun ChildFullResponse.toChildFullData(): ChildFullData {

    return ChildFullData(
        numberOfGuardians = numOfGuardians ,
        childId = child.childId,
        firstName = child.firstName,
        lastName = child.lastName,
        fatherFirstName = child.fatherFirstName,
        fatherLastName = child.fatherLastName,
        motherFirstName = child.motherFirstName,
        motherLastName = child.motherLastName,
        dateOfBirth = child.dateOfBirth,
        birthCertificateImgUrl = "${ApiRoutes.BASE_URL}/${child.birthCertificateImgUrl}",
        gender = child.gender,
        employeeId = child.employeeId
    )
}

internal fun ChildFullData.toAddChildRequest(): AddChildRequest =
    AddChildRequest(
        firstName = firstName,
        fatherFirstName = motherFirstName,
        fatherLastName =fatherLastName,
        lastName = lastName,
        motherFirstName = motherFirstName,
        motherLastName = motherLastName,
        dateOfBirth = dateOfBirth,
        gender = gender
    )

internal fun AddChildResponse.toChildFullData(): ChildFullData =
    ChildFullData(
        childId = child.childId,
        firstName = child.firstName,
        lastName = child.lastName,
        fatherFirstName = child.fatherFirstName,
        fatherLastName = child.fatherLastName,
        motherFirstName = child.motherFirstName,
        motherLastName = child.motherLastName,
        dateOfBirth = child.dateOfBirth,
        gender = child.gender,
        employeeId = child.employeeId,
    )

internal fun ChildFullDto.toChildFullData(): ChildFullData = ChildFullData(
    childId = childId,
    firstName = firstName,
    lastName = lastName,
    fatherFirstName = fatherFirstName,
    fatherLastName = fatherLastName,
    motherFirstName =motherFirstName,
    motherLastName = motherLastName,
    dateOfBirth = dateOfBirth,
    birthCertificateImgUrl = "${ApiRoutes.BASE_URL}/$birthCertificateImgUrl",
    gender = gender,
    employeeId = employeeId,
    employeeName = user?.let {user-> "${user.firstName} ${user.lastName}" }
)