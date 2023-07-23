package com.gicproject.salamkioskapp.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class SelectDepartment(
    @SerializedName("Department_PK_ID"        ) var DepartmentPKID         : Int?    = null,
    @SerializedName("Department_Name_EN"      ) var DepartmentNameEN       : String? = null,
    @SerializedName("Department_Name_AR"      ) var DepartmentNameAR       : String? = null,
    @SerializedName("Result"                  ) var Result                 : Int?    = null,
    @SerializedName("Department_Descriptions" ) var DepartmentDescriptions : String? = null,
    @SerializedName("SAP"                     ) var SAP                    : String? = null,
    @SerializedName("Alert_Message"           ) var AlertMessage           : String? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(DepartmentPKID)
        parcel.writeString(DepartmentNameEN)
        parcel.writeString(DepartmentNameAR)
        parcel.writeValue(Result)
        parcel.writeString(DepartmentDescriptions)
        parcel.writeString(SAP)
        parcel.writeString(AlertMessage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SelectDepartment> {
        override fun createFromParcel(parcel: Parcel): SelectDepartment {
            return SelectDepartment(parcel)
        }

        override fun newArray(size: Int): Array<SelectDepartment?> {
            return arrayOfNulls(size)
        }
    }
}



