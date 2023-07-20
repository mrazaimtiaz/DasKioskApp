package com.gicproject.salamkioskapp.domain.model

import com.google.gson.annotations.SerializedName

data class Counter(
    @SerializedName("Counter_PK_ID") var PKID: Int? = null,
    @SerializedName("Counter_Description") var CounterDes: String? = null,
    @SerializedName("Counter_MachineName") var CounterName: String? = null
)