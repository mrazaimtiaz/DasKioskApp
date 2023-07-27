package com.gicproject.salamkioskapp.presentation

import com.gicproject.salamkioskapp.domain.model.ConsultVisit
import com.gicproject.salamkioskapp.domain.model.Patient
import com.gicproject.salamkioskapp.domain.model.SelectDepartment
import com.gicproject.salamkioskapp.domain.model.SelectService


sealed class MyEvent {
    object GetBranches: MyEvent()
    object GetCounters: MyEvent()
    object GetDepartments: MyEvent()
    object GetSelectDepartments: MyEvent()
    object GetSelectOptions: MyEvent()
    data class GetSelectServices(val deptId: String) : MyEvent()
    data class GetChildTestServices(val parentId: String) : MyEvent()
    object GetSelectTestServices : MyEvent()
    data class GetCheckIsWalkIn(val serviceId: String) : MyEvent()
    data class CheckCivilIDInSap(val civilId: String,val isAppointment: Boolean?,) : MyEvent()
    data class CancelConsultVisit(val consultVisit: ConsultVisit,val isCancel:Boolean, val isBack:Boolean) : MyEvent()
    data class CreateConsultVisit(val selectDepartment: SelectDepartment?, val patient: Patient?,
                                  val service: SelectService?,) : MyEvent()
    data class PayKnet(val selectDepartment: SelectDepartment?, val patient: Patient?,
                                  val service: SelectService?,) : MyEvent()
    data class CreateInvoice(val selectDepartment: SelectDepartment?, val patient: Patient?,
                       val service: SelectService?,val consultVisit: ConsultVisit?,) : MyEvent()



    data class GetDoctor(val parentId: String) : MyEvent()
    object GetPrintTicket: MyEvent()
    data class GetBookTicket(
        val isCivilIdPage: Boolean,
        val serviceID: String,
        val isHandicap: Boolean,
        val isVip: Boolean,
        val languageID: String,
        val appointmentCode: String,
        val  isaapt: Boolean,
        val  refid: String,
        val DoctorServiceID: String,val ticketDesignId: String) : MyEvent()
}
