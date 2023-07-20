package com.gicproject.salamkioskapp.domain.model

import com.google.gson.annotations.SerializedName


data class SelectDepartment(
    @SerializedName("Department_PK_ID"        ) var DepartmentPKID         : Int?    = null,
    @SerializedName("Department_Name_EN"      ) var DepartmentNameEN       : String? = null,
    @SerializedName("Department_Name_AR"      ) var DepartmentNameAR       : String? = null,
    @SerializedName("Result"                  ) var Result                 : Int?    = null,
    @SerializedName("Department_Descriptions" ) var DepartmentDescriptions : String? = null,
    @SerializedName("SAP"                     ) var SAP                    : String? = null,
    @SerializedName("Alert_Message"           ) var AlertMessage           : String? = null
)



