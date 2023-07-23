package com.gicproject.salamkioskapp.domain.model

import android.os.Parcel
import android.os.Parcelable
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
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Patientname)
        parcel.writeString(PatientnameAr)
        parcel.writeString(Patientid)
        parcel.writeString(Mobileno)
        parcel.writeString(docnamear)
        parcel.writeString(CIVILID)
        parcel.writeString(alertMessage)
        parcel.writeValue(Result)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Patient> {
        override fun createFromParcel(parcel: Parcel): Patient {
            return Patient(parcel)
        }

        override fun newArray(size: Int): Array<Patient?> {
            return arrayOfNulls(size)
        }
    }
}




