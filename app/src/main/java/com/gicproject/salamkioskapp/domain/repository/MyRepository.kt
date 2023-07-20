package com.gicproject.salamkioskapp.domain.repository

import com.gicproject.salamkioskapp.domain.model.*
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MyRepository {

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



