package com.gicproject.salamkioskapp.domain.model

import com.google.gson.annotations.SerializedName
data class SelectOption(
    @SerializedName("Department_PK_ID"        ) var DepartmentPKID         : Int?    = null,
    @SerializedName("Department_Name_EN"      ) var DepartmentNameEN       : String? = null,
    @SerializedName("Department_Name_AR"      ) var DepartmentNameAR       : String? = null,
    @SerializedName("Department_Location"     ) var DepartmentLocation     : String? = null,
    @SerializedName("Department_Descriptions" ) var DepartmentDescriptions : String? = null,
    @SerializedName("Department_Parent_ID"    ) var DepartmentParentID     : Int?    = null,
    @SerializedName("Department_ClinicID"     ) var DepartmentClinicID     : Int?    = null
)





