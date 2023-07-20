package com.gicproject.salamkioskapp.domain.model


import com.google.gson.annotations.SerializedName


data class Invoice (

    @SerializedName("invoicenumber" ) var invoicenumber : String? = null,
    @SerializedName("billdate"      ) var billdate      : String? = null,
    @SerializedName("invoiceamount" ) var invoiceamount : String? = null,
    @SerializedName("patientcase"   ) var patientcase   : String? = null,
    @SerializedName("att_phy"       ) var attPhy        : String? = null,
    @SerializedName("item"          ) var item          : String? = null,
    @SerializedName("itemcode"      ) var itemcode      : String? = null,
    @SerializedName("itemtext"      ) var itemtext      : String? = null,
    @SerializedName("qty"           ) var qty           : String? = null,
    @SerializedName("unitprice"     ) var unitprice     : String? = null,
    @SerializedName("netprice"      ) var netprice      : String? = null,
    @SerializedName("itemsq"        ) var itemsq        : String? = null,
    @SerializedName("dept"          ) var dept          : String? = null,
    @SerializedName("report_link"   ) var reportLink    : String? = null,
    @SerializedName("alert_message" ) var alertMessage  : String? = null,
    @SerializedName("Result"        ) var Result        : Int?    = null

)