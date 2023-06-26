package com.gicproject.salamkioskapp.presentation

import com.gicproject.salamkioskapp.domain.model.Department
import com.gicproject.salamkioskapp.domain.model.Doctor


data class SelectDoctorScreenState(
    val isLoading: Boolean = false,
    val error: String = "",
    val isApiLoading: Boolean = false,
    val doctors: List<Doctor> = emptyList(),
)
