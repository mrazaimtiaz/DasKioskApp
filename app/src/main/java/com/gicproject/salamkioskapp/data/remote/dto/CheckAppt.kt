package com.gicproject.salamkioskapp.data.remote.dto

import com.gicproject.salamkioskapp.domain.model.Department
import com.google.gson.annotations.SerializedName
data class CheckAppt (
    @SerializedName("ApptExist" ) var ApptExist : Boolean? = null
)





