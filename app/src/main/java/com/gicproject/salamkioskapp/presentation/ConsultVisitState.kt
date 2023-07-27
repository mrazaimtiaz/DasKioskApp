package com.gicproject.salamkioskapp.presentation

import com.gicproject.salamkioskapp.domain.model.ConsultVisit


data class ConsultVisitState(
    val isLoading: Boolean = false,
    val error: String = "",
    val isBack: Boolean = false,
    val isCancel: Boolean = false,
    val isApiLoading: Boolean = false,
    val consultVisit: ConsultVisit? = null,
)
