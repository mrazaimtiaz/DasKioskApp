package com.gicproject.salamkioskapp.domain.model

import com.google.gson.annotations.SerializedName
data class BookTicket(

    @SerializedName("NewPKID"       ) var NewPKID       : Int?    = null,
    @SerializedName("BookedNo"      ) var BookedNo      : String? = null,
    @SerializedName("QueueSize"     ) var QueueSize     : Int?    = null,
    @SerializedName("EstimatedTime" ) var EstimatedTime : Int?    = null,
    @SerializedName("PrintTime"     ) var PrintTime     : String? = null
)



