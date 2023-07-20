package com.gicproject.salamkioskapp.domain.model

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("x_status") var status: String? = null,
    @SerializedName("x_message") var message: String? = null,
)