package com.gicproject.salamkioskapp.domain.model

import com.google.gson.annotations.SerializedName

data class Branch(
    @SerializedName("Branch_PK_ID") var PKID: Int? = null,
    @SerializedName("Branch_Name_EN") var BranchNameEn: String? = null,
    @SerializedName("Branch_Name_AR") var BranchNameAr: String? = null,
    @SerializedName("Branch_IsActive") var IsBranchActive: Boolean? = null
)