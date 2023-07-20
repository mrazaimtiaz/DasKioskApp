package com.gicproject.salamkioskapp.data.repository


import com.gicproject.salamkioskapp.data.remote.MyApi
import com.gicproject.salamkioskapp.domain.model.*
import com.gicproject.salamkioskapp.domain.repository.MyRepository
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val api: MyApi
) : MyRepository {

    override suspend fun getSelectTestService(
        branchId: String,
        ParentID: String,
    ): List<SelectService>?{
        return api.getSelectTestService(branchId,ParentID)
    }


    override suspend fun getDoctorsWithTime(branchId: String,parentId: String): List<Doctor>? {
        return api.getDoctorsWithTime(branchId,parentId)
    }

    override suspend fun getCivilIdAppointment(civilId: String,serviceId: String): List<CheckAppt>? {
        return api.getCivilIdAppointment(civilId,serviceId)
    }

    override suspend fun checkIsWalkIn(serviceId: String): List<CheckIsWalkIn>? {
        return api.checkIsWalkIn(serviceId)
    }

    override suspend fun getBookTicket(
        serviceID: String,
        isHandicap: Boolean,
        isVip: Boolean,
        languageID: String,
        appointmentCode: String,
        isaapt: Boolean,
        refid: String,
        DoctorServiceID: String,
    ): List<BookTicket>? {
        return api.getBookTicket(serviceID, isHandicap,isVip,languageID,appointmentCode,isaapt,refid,DoctorServiceID)
    }

    override suspend fun getTicket(
        QueueID: Int,language: Int
    ): List<GetTicket>? {
        return api.getTicket(QueueID, language)

    }


    override suspend fun getSelectServices(
        branchId: String,
        deptId: String,
    ): List<SelectService>? {
        return api.getSelectServices(branchId, deptId)
    }

    override suspend fun getOptions(
        branchId: String,
        deptParentId: String,
    ): List<SelectOption>? {
        return api.getOptions(branchId, deptParentId)
    }

    override suspend fun getTestServiceOptions(
        branchId: String,
        deptParentId: String,
    ):  List<SelectOption>? {
        return api.getTestServiceOptions(branchId, deptParentId)
    }


    override suspend fun getSelectDepartments(
        branchId: String,
        deptParentId: String,
    ): List<SelectDepartment>? {
        return api.getDepartmentList(branchId, "en")
    }


    override suspend fun getDepartments(): List<Department>? {
        return api.getDepartments()
    }

    override suspend fun getCounters(branchId: String): List<Counter>? {
        return api.getCounters(branchId)
    }

    override suspend fun getBranches(): List<Branch>? {
        return api.getBranches()
    }


}