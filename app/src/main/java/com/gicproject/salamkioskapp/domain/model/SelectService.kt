package com.gicproject.salamkioskapp.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class SelectService(


    @SerializedName("Services_PK_ID"   ) var ServicesPKID: Int?    = null,
    @SerializedName("Services_Name_EN" ) var ServicesNameEN: String? = null,
    @SerializedName("Services_Name_AR" ) var ServicesNameAR: String? = null,
    @SerializedName("Result"           ) var Result: Int?    = null,
    @SerializedName("Waiting"          ) var Waiting: String? = null,
    @SerializedName("Doctor_ID_SAP"    ) var DoctorIDSAP: String? = null,
    @SerializedName("Alert_Message"    ) var AlertMessage: String? = null,
    @SerializedName("Logo"             ) var Logo: Int?    = null,
    @SerializedName("CONS_FEE"         ) var CONSFEE: Double? = null,
    @SerializedName("DOC_NAME"         ) var DOCNAME: String? = null,
    @SerializedName("Sapid"            ) var Sapid: String? = null,
    @SerializedName("sap_Logo"         ) var sapLogo: String? = null,
    @SerializedName("DOC_NAMEAR"       ) var DOCNAMEAR: String? = null,
    @SerializedName("sap_Waiting"      ) var sapWaiting: String? = null,
    @SerializedName("ATT_PHYS"         ) var ATTPHYS: String? = null,
    @SerializedName("nurse"            ) var nurse: String? = null,
    @SerializedName("sap_jobtitle"     ) var sapJobtitle: String? = null,
    @SerializedName("doctoridsap"      ) var doctoridsap: String? = null,
    @SerializedName("jobtitlear"       ) var jobtitlear: String? = null,
    @SerializedName("jobtitle"         ) var jobtitle: String? = null,
    @SerializedName("sapids"           ) var sapids: String? = null,
    @SerializedName("nursid"           ) var nursid: String? = null,
    @SerializedName("Orgid"            ) var Orgid: String? = null


) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(ServicesPKID)
        parcel.writeString(ServicesNameEN)
        parcel.writeString(ServicesNameAR)
        parcel.writeValue(Result)
        parcel.writeString(Waiting)
        parcel.writeString(DoctorIDSAP)
        parcel.writeString(AlertMessage)
        parcel.writeValue(Logo)
        parcel.writeValue(CONSFEE)
        parcel.writeString(DOCNAME)
        parcel.writeString(Sapid)
        parcel.writeString(sapLogo)
        parcel.writeString(DOCNAMEAR)
        parcel.writeString(sapWaiting)
        parcel.writeString(ATTPHYS)
        parcel.writeString(nurse)
        parcel.writeString(sapJobtitle)
        parcel.writeString(doctoridsap)
        parcel.writeString(jobtitlear)
        parcel.writeString(jobtitle)
        parcel.writeString(sapids)
        parcel.writeString(nursid)
        parcel.writeString(Orgid)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SelectService> {
        override fun createFromParcel(parcel: Parcel): SelectService {
            return SelectService(parcel)
        }

        override fun newArray(size: Int): Array<SelectService?> {
            return arrayOfNulls(size)
        }
    }
}





