package com.gicproject.salamkioskapp.presentation

import com.gicproject.salamkioskapp.domain.model.Department
import com.gicproject.salamkioskapp.domain.model.Patient


data class InsertKnetScreenState(
    val isLoading: Boolean = false,
    val error: String = "",
    val success: String = "",
)
