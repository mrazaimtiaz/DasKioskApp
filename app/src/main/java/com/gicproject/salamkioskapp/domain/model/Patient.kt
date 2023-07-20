package com.gicproject.salamkioskapp.domain.model

import com.gicproject.salamkioskapp.domain.model.Patient
import com.google.gson.annotations.SerializedName


data class Patient(
    @SerializedName("Patientname"   ) var Patientname   : String? = null,
    @SerializedName("PatientnameAr" ) var PatientnameAr : String? = null,
    @SerializedName("Patientid"     ) var Patientid     : String? = null,
    @SerializedName("Mobileno"      ) var Mobileno      : String? = null,
    @SerializedName("docnamear"     ) var docnamear     : String? = null,
    @SerializedName("CIVILID"       ) var CIVILID       : String? = null,
    @SerializedName("alert_message" ) var alertMessage  : String? = null,
    @SerializedName("Result"        ) var Result        : Int?    = null
)




