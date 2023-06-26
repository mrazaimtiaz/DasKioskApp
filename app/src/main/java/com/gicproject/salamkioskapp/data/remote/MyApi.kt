package com.gicproject.salamkioskapp.data.remote


import com.gicproject.salamkioskapp.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface MyApi {

    @GET("api/getDoctorsWithTime")
    suspend fun getDoctorsWithTime(
        @Query("branchid")
        branchId: String,
        @Query("ParentID")
        ParentID: String,
    ): List<DoctorDto>?

    @GET("api/getOptions")
    suspend fun getOptions(
        @Query("branchid")
        branchId: String,
        @Query("DeptParentID")
        DeptParentID: String,
    ): List<SelectOptionDto>?


    @POST("api/checkAppt")
    suspend fun getCivilIdAppointment(
        @Query("civilID")
        civilID: String,
        @Query("ServiceID")
        serviceID: String,
    ): List<CheckAppt>?

    @POST("api/Checkwalkinappt")
    suspend fun checkIsWalkIn(
        @Query("ServiceID")
        serviceId: String,
    ): List<CheckIsWalkIn>?

    @POST("api/BookTicket")
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
    ): List<BookTicketDto>?

    @GET("api/GetTicket")
    suspend fun getTicket(@Query("QueueID") QueueID: Int, @Query("language") language: Int): List<GetTicketDto>?


    @GET("api/GetServices")
    suspend fun getSelectServices(
        @Query("branchid")
        branchId: String,
        @Query("DeptParentID")
        deptId: String,
    ): List<SelectServiceDto>?


    @GET("api/BranchDept")
    suspend fun getSelectDepartments(
        @Query("branchid")
        branchId: String,
        @Query("DeptParentID")
        DeptParentID: String,
    ): List<SelectDepartmentDto>?

    @GET("api/dept")
    suspend fun getDepartments(
    ): List<DepartmentDto>?

    @GET("api/Counters")
    suspend fun getCounters(
        @Query("branchid")
        branchId: String,
    ): List<CounterDto>?

    @GET("api/getbranches")
    suspend fun getBranches(
    ): List<BranchDto>?



}