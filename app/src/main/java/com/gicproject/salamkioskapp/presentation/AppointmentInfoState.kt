package com.gicproject.salamkioskapp.presentation

import com.gicproject.salamkioskapp.domain.model.ConsultVisit
import com.gicproject.salamkioskapp.domain.model.Department
import com.gicproject.salamkioskapp.domain.model.Doctor
import com.gicproject.salamkioskapp.domain.model.SelectService


data class AppointmentInfoState(
    val isLoading: Boolean = false,
    val error: String = "",
    val isApiLoading: Boolean = false,
    val consultVisit: ConsultVisit? = null,
)
