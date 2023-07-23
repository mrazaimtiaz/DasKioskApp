package com.gicproject.salamkioskapp.data.remote


import com.gicproject.salamkioskapp.domain.model.*
import com.gicproject.salamkioskapp.emvnfccard.model.Service
import retrofit2.Response
import retrofit2.http.*

interface MyApi {


    @GET("SelfServiceAPI/api/getdepartmentlist")
    suspend fun getDepartmentList(
        @Query("branchid")
        branchid: String,
        @Query("lang")
        lang: String,
    ): List<SelectDepartment>?

    @GET("SelfServiceAPI/api/CheckCivilIDinSAP")
    suspend fun checkCivilIDinSAP(
        @Query("civilid")
        civilid: String,
        @Query("lang")
        lang: String,
    ): List<Patient>?

    @GET("SelfServiceAPI/api/getdoctorlist")
    suspend fun getdoctorlist(
        @Query("deptid")
        deptid: String,
        @Query("lang")
        lang: String,
    ): List<SelectService>?


    @GET("SelfServiceAPI/api/CreateConsultVisit")
    suspend fun createConsultVisit(
        @Query("patientid")
        patientid: String,
        @Query("department")
        department: String,
        @Query("nurseou")
        nurseou: String,
        @Query("att_phys")
        att_phys: String,
        @Query("appidlbl")
        appidlbl: String,
        @Query("CIVILID")
        CIVILID: String,
        @Query("PntName")
        PntName: String,
        @Query("pntnamear")
        pntnamear: String,
        @Query("TerminalId")
        TerminalId: String,
        @Query("BranchID")
        BranchID: String,
        @Query("lang")
        lang: String,
    ): List<ConsultVisit>?


    @GET("SelfServiceAPI/api/CreateInvoice")
    suspend fun createInvoice(
        @Query("docsapid")
        docsapid: String,
        @Query("patientid")
        patientid: String,
        @Query("caseid")
        caseid: String,
        @Query("sequence")
        sequence: String,
        @Query("Auth")
        Auth: String,
        @Query("Cardtype")
        Cardtype: String,
        @Query("PntName")
        PntName: String,
        @Query("BranchID")
        BranchID: String,
        @Query("DocName")
        DocName: String,
        @Query("transactionno")
        transactionno: String,
        @Query("authcode")
        authcode: String,
        @Query("cardno")
        cardno: String,
        @Query("recipts")
        recipts: String,
        @Query("bankname")
        bankname: String,
        @Query("MerchaintId")
        MerchaintId: String,
        @Query("TerminalId")
        TerminalId: String,
        @Query("lang")
        lang: String,
    ): List<Invoice>?




    @GET("SelfServiceAPI/api/CancelVisit")
    suspend fun cancelVisit(
        @Query("E_MOVEMNT_SEQNOlbl")
        E_MOVEMNT_SEQNOlbl: String,
        @Query("caseidlbl")
        caseidlbl: String,
        @Query("Servicelbl_id")
        Servicelbl_id: String,
        @Query("BranchID")
        BranchID: String,
        @Query("lang")
        lang: String,
    ): List<SelectDepartment>?


    @GET("SelfServiceAPI/api/selectTest")
    suspend fun getSelectTestService(
        @Query("branchid")
        branchId: String,
        @Query("ParentID")
        ParentID: String,
    ): List<SelectService>?


    @GET("SelfServiceAPI/api/getDoctorsWithTime")
    suspend fun getDoctorsWithTime(
        @Query("branchid")
        branchId: String,
        @Query("ParentID")
        ParentID: String,
    ): List<Doctor>?

    @GET("SelfServiceAPI/api/getOptions")
    suspend fun getOptions(
        @Query("branchid")
        branchId: String,
        @Query("DeptParentID")
        DeptParentID: String,
    ): List<SelectOption>?


    @GET("SelfServiceAPI/api/getTestServiceOptions")
    suspend fun getTestServiceOptions(
        @Query("branchid")
        branchId: String,
        @Query("DeptParentID")
        DeptParentID: String,
    ): List<SelectOption>?


    @POST("SelfServiceAPI/api/checkAppt")
    suspend fun getCivilIdAppointment(
        @Query("civilID")
        civilID: String,
        @Query("ServiceID")
        serviceID: String,
    ): List<CheckAppt>?

    @POST("SelfServiceAPI/api/Checkwalkinappt")
    suspend fun checkIsWalkIn(
        @Query("ServiceID")
        serviceId: String,
    ): List<CheckIsWalkIn>?

    @POST("SelfServiceAPI/api/BookTicket")
    suspend fun getBookTicket(
        @Query("serviceID")
        serviceID: String,
        @Query("IsHandicap")
        isHandicap: Boolean,
        @Query("isVip")
        isVip: Boolean,
        @Query("languageID")
        languageID: String,
        @Query("AppointmentCode")
        appointmentCode: String,
        @Query("isaapt")
        isaapt: Boolean,
        @Query("refid")
        refid: String,
        @Query("DoctorServiceID")
        DoctorServiceID: String,
    ): List<BookTicket>?

    @GET("SelfServiceAPI/api/GetTicket")
    suspend fun getTicket(@Query("QueueID") QueueID: Int, @Query("language") language: Int): List<GetTicket>?


    @GET("SelfServiceAPI/api/GetServices")
    suspend fun getSelectServices(
        @Query("branchid")
        branchId: String,
        @Query("DeptParentID")
        deptId: String,
    ): List<SelectService>?


    @GET("screensapi/api/dept")
    suspend fun getDepartments(
    ): List<Department>?

    @GET("screensapi/api/Counters")
    suspend fun getCounters(
        @Query("branchid")
        branchId: String,
    ): List<Counter>?

    @GET("screensapi/api/Branches")
    suspend fun getBranches(
    ): List<Branch>?



}