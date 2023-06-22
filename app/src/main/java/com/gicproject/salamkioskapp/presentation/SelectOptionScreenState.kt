package com.gicproject.salamkioskapp.presentation

import com.gicproject.salamkioskapp.domain.model.Department
import com.gicproject.salamkioskapp.domain.model.SelectDepartment
import com.gicproject.salamkioskapp.domain.model.SelectOption


data class SelectOptionScreenState(
    val isLoading: Boolean = false,
    val isApiLoading: Boolean = false,
    val error: String = "",
    val options: List<SelectOption> = emptyList(),
)
