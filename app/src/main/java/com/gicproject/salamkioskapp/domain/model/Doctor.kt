package com.gicproject.salamkioskapp.domain.model

import com.google.gson.annotations.SerializedName

data class Doctor(
    @SerializedName("id") var id: String? = null,
    @SerializedName("nameEn") var nameEn: String? = null,
    @SerializedName("departmentEn") var departmentEn: String? = null,
    @SerializedName("price") var price: String? = null,
    @SerializedName("time") var time: String? = null,
    @SerializedName("date") var date: String? = null,
)