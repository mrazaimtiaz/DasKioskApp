package com.gicproject.salamkioskapp.domain.model


import com.google.gson.annotations.SerializedName


data class ConsultVisit (

    @SerializedName("E_MOVEMNT_SEQNOlbl" ) var EMOVEMNTSEQNOlbl : String? = null,
    @SerializedName("consfees"           ) var consfees         : String? = null,
    @SerializedName("pntidtxt"           ) var pntidtxt         : String? = null,
    @SerializedName("CIVILID"            ) var CIVILID          : String? = null,
    @SerializedName("pntnameenlbl"       ) var pntnameenlbl     : String? = null,
    @SerializedName("att_physinv"        ) var attPhysinv       : String? = null,
    @SerializedName("patientnamearlbl"   ) var patientnamearlbl : String? = null,
    @SerializedName("caseidlbl"          ) var caseidlbl        : String? = null,
    @SerializedName("Servicelbl"         ) var Servicelbl       : String? = null,
    @SerializedName("Servicelblid"       ) var Servicelblid     : String? = null,
    @SerializedName("alert_message"      ) var alertMessage     : String? = null,
    @SerializedName("Result"             ) var Result           : Int?    = null

)