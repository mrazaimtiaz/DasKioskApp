package com.gicproject.salamkioskapp.domain.model

import com.google.gson.annotations.SerializedName


data class SelectService (


    @SerializedName("Services_PK_ID"   ) var ServicesPKID   : Int?    = null,
    @SerializedName("Services_Name_EN" ) var ServicesNameEN : String? = null,
    @SerializedName("Services_Name_AR" ) var ServicesNameAR : String? = null,
    @SerializedName("Result"           ) var Result         : Int?    = null,
    @SerializedName("Waiting"          ) var Waiting        : String? = null,
    @SerializedName("Doctor_ID_SAP"    ) var DoctorIDSAP    : String? = null,
    @SerializedName("Alert_Message"    ) var AlertMessage   : String? = null,
    @SerializedName("Logo"             ) var Logo           : Int?    = null,
    @SerializedName("CONS_FEE"         ) var CONSFEE        : Int?    = null,
    @SerializedName("DOC_NAME"         ) var DOCNAME        : String? = null,
    @SerializedName("Sapid"            ) var Sapid          : String? = null,
    @SerializedName("sap_Logo"         ) var sapLogo        : String? = null,
    @SerializedName("DOC_NAMEAR"       ) var DOCNAMEAR      : String? = null,
    @SerializedName("sap_Waiting"      ) var sapWaiting     : String? = null,
    @SerializedName("ATT_PHYS"         ) var ATTPHYS        : String? = null,
    @SerializedName("nurse"            ) var nurse          : String? = null,
    @SerializedName("sap_jobtitle"     ) var sapJobtitle    : String? = null,
    @SerializedName("doctoridsap"      ) var doctoridsap    : String? = null,
    @SerializedName("jobtitlear"       ) var jobtitlear     : String? = null,
    @SerializedName("jobtitle"         ) var jobtitle       : String? = null,
    @SerializedName("sapids"           ) var sapids         : String? = null,
    @SerializedName("nursid"           ) var nursid         : String? = null,
    @SerializedName("Orgid"            ) var Orgid          : String? = null


)





