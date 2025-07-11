package com.example.model.vaccine

data class GenericVaccinationTable(
    val visits: List<VaccinationTableVisit>
)

data class VaccinationTableVisit(
    val visitNumber: Int,
    val vaccines: List<VaccineMainInfo>
)

data class VaccineMainInfo(
    val id: Int,
    val name: String
)