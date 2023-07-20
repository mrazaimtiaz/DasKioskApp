package com.gicproject.salamkioskapp.data.remote


import com.gicproject.salamkioskapp.domain.model.*
import retrofit2.Response
import retrofit2.http.*

interface MyApi {


    @GET("api/selectTest")
    suspend fun getSelectTestService(
        @Query("branchid")
        branchId: String,
        @Query("ParentID")
        ParentID: String,
    ): List<SelectService>?


    @GET("api/getDoctorsWithTime")
    suspend fun getDoctorsWithTime(
        @Query("branchid")
        branchId: String,
        @Query("ParentID")
        ParentID: String,
    ): List<Doctor>?

    @GET("api/getOptions")
    suspend fun getOptions(
        @Query("branchid")
        branchId: String,
        @Query("DeptParentID")
        DeptParentID: String,
    ): List<SelectOption>?


    @GET("api/getTestServiceOptions")
    suspend fun getTestServiceOptions(
        @Query("branchid")
        branchId: String,
        @Query("DeptParentID")
        DeptParentID: String,
    ): List<SelectOption>?


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
    ): List<BookTicket>?

    @GET("api/GetTicket")
    suspend fun getTicket(@Query("QueueID") QueueID: Int, @Query("language") language: Int): List<GetTicket>?


    @GET("api/GetServices")
    suspend fun getSelectServices(
        @Query("branchid")
        branchId: String,
        @Query("DeptParentID")
        deptId: String,
    ): List<SelectService>?


    @GET("api/getdepartmentlist")
    suspend fun getDepartmentList(
        @Query("branchid")
        branchid: String,
        @Query("lang")
        lang: String,
    ): List<SelectDepartment>?

    @GET("api/dept")
    suspend fun getDepartments(
    ): List<Department>?

    @GET("api/Counters")
    suspend fun getCounters(
        @Query("branchid")
        branchId: String,
    ): List<Counter>?

    @GET("api/getbranches")
    suspend fun getBranches(
    ): List<Branch>?



}