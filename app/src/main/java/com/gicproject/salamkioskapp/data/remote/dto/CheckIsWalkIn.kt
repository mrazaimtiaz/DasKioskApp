package com.gicproject.salamkioskapp.data.remote.dto

import com.gicproject.salamkioskapp.domain.model.Department
import com.google.gson.annotations.SerializedName
data class CheckIsWalkIn (
    @SerializedName("IsAvailable" ) var IsAvailable : Boolean? = null,
    @SerializedName("ApptServiceID" ) var ApptServiceID : Int? = null
)





