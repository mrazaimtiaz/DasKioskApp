package com.gicproject.salamkioskapp.presentation

import com.gicproject.salamkioskapp.domain.model.Department
import com.gicproject.salamkioskapp.domain.model.Doctor
import com.gicproject.salamkioskapp.domain.model.SelectService
import com.gicproject.salamkioskapp.emvnfccard.model.Service


data class SelectChildTestServiceScreenState(
    val isLoading: Boolean = false,
    val error: String = "",
    val isApiLoading: Boolean = false,
    val services: List<SelectService> = emptyList(),
)
