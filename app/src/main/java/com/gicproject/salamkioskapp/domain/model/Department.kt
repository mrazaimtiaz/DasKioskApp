package com.gicproject.salamkioskapp.domain.model

import com.google.gson.annotations.SerializedName
data class Department(
    @SerializedName("ParentID") var ParentID: Int? = null,
    @SerializedName("Department_Name_EN") var DepartmentNameEn: String? = null,
    @SerializedName("Department_Name_AR") var DepartmentNameAr: String? = null,
)




