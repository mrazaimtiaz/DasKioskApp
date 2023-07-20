package com.gicproject.salamkioskapp.domain.repository

import com.gicproject.salamkioskapp.domain.model.*
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MyRepository {



    suspend fun getDepartmentList(
        branchid: String,
        lang: String,
    ): List<SelectDepartment>?

    suspend fun checkCivilIDinSAP(
        civilid: String,
        lang: String,
    ): List<Patient>?

    suspend fun getdoctorlist(
        deptid: String,
        lang: String,
    ): List<SelectService>?


    suspend fun createConsultVisit(
        patientid: String,
        department: String,
        nurseou: String,
        att_phys: String,
        appidlbl: String,
        CIVILID: String,
        PntName: String,
        pntnamear: String,
        TerminalId: String,
        BranchID: String,
        lang: String,
    ): List<ConsultVisit>?


    suspend fun createInvoice(
        docsapid: String,
        patientid: String,
        caseid: String,
        sequence: String,
        Auth: String,
        Cardtype: String,
        PntName: String,
        BranchID: String,
        DocName: String,
        transactionno: String,
        authcode: String,
        cardno: String,
        recipts: String,
        bankname: String,
        MerchaintId: String,
        TerminalId: String,
        lang: String,
    ): List<Invoice>?


    suspend fun cancelVisit(
        E_MOVEMNT_SEQNOlbl: String,
        caseidlbl: String,
        Servicelbl_id: String,
        BranchID: String,
        lang: String,
    ): List<SelectDepartment>?


    suspend fun getDoctorsWithTime(
        branchId: String,
        ParentID: String,
    ): List<Doctor>?

    suspend fun getSelectTestService(
        branchId: String,
        ParentID: String,
    ): List<SelectService>?

    suspend fun getCivilIdAppointment(civilId: String,serviceId: String): List<CheckAppt>?

    suspend fun checkIsWalkIn(
        serviceId: String,
    ): List<CheckIsWalkIn>?
    suspend fun getBookTicket(
        serviceID: String,
        isHandicap: Boolean,
        isVip: Boolean,
        languageID: String,
        appointmentCode: String,
        isaapt: Boolean,
        refid: String,
        DoctorServiceID: String,
    ): List<BookTicket>?

    suspend fun getTicket(QueueID: Int,language: Int): List<GetTicket>?


    suspend fun getSelectServices(
        @Query("branchid")
        branchId: String,
        @Query("DeptParentID")
        deptId: String,
    ): List<SelectService>?

    suspend fun getOptions(
        branchId: String,
        deptParentId: String,
    ): List<SelectOption>?

    suspend fun getTestServiceOptions(
        branchId: String,
        deptParentId: String,
    ): List<SelectOption>?

    suspend fun getSelectDepartments(
        branchId: String,
        deptParentId: String,
    ): List<SelectDepartment>?

    suspend fun getDepartments(
    ): List<Department>?

    suspend fun getCounters(
        branchId: String,
    ): List<Counter>?

    suspend fun getBranches(
    ): List<Branch>?


}



