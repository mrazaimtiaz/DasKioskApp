package com.gicproject.salamkioskapp.presentation

import com.gicproject.salamkioskapp.domain.model.Department
import com.gicproject.salamkioskapp.domain.model.Invoice
import com.gicproject.salamkioskapp.domain.model.Patient
import com.gicproject.salamkioskapp.domain.use_case.CreateInvoice


data class CreateInvoiceScreenState(
    val isLoading: Boolean = false,
    val error: String = "",
    val success: String = "",
    val invoice: Invoice? = null,
)
