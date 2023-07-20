package com.gicproject.salamkioskapp.domain.use_case

import com.gicproject.dasdoctorcvapp.domain.use_case.GetBranches

data class MyUseCases(
    val getDepartmentList: GetDepartmentList,
    val checkCivilIDinSAP:CheckCivilIDinSAP,
    val getDoctorList:GetDoctorList,
    val createConsultVisit:CreateConsultVisit,
    val createInvoice:CreateInvoice,
    val cancelVisit:CancelVisit,
    val getBranches: GetBranches,
    val getCounters: GetCounters,
    val getDepartments: GetDepartments,
    val getSelectDepartments: GetSelectDepartments,
    val getSelectOptions: GetSelectOptions,
    val getSelectTestServices: GetSelectTestServices,
    val getDoctors: GetDoctors,
    val getPrintTicket: GetPrintTicket,
    val getSelectServices: GetSelectServices,
    val getBookTicket: GetBookTicket,
    val getTicket: GetTicket,
    val getCheckIsWalkIn: GetCheckIsWalkIn,
    val getCivilIdAppointment: GetCivilIdAppointment,
    val getChildTestServices: GetChildTestServices,
)
