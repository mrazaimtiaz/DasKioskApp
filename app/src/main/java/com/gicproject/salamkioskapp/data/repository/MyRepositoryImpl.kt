package com.gicproject.salamkioskapp.data.repository


import com.gicproject.salamkioskapp.data.remote.MyApi
import com.gicproject.salamkioskapp.domain.model.*
import com.gicproject.salamkioskapp.domain.repository.MyRepository
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val api: MyApi
) : MyRepository {


    override suspend fun getDepartmentList(
        branchid: String,
        lang: String,
    ): List<SelectDepartment>?  {
        return api.getDepartmentList(branchid,lang)
    }


    override suspend fun checkCivilIDinSAP(
        civilid: String,
        lang: String,
    ): List<Patient>? {
        return api.checkCivilIDinSAP(civilid,lang)
    }

    override suspend fun getdoctorlist(
        deptid: String,
        lang: String,
    ): List<SelectService>?{
        return api.getdoctorlist(deptid,lang)
    }


    override suspend fun createConsultVisit(
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
    ): List<ConsultVisit>? {
        return  api.createConsultVisit(patientid,department,nurseou,att_phys,appidlbl,CIVILID,PntName,pntnamear,TerminalId,BranchID,lang)
    }


    override suspend fun createInvoice(
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
    ): List<Invoice>? {
        return  api.createInvoice(docsapid,patientid,caseid,sequence,Auth,Cardtype,PntName,BranchID,DocName,transactionno,authcode,cardno,recipts,bankname,MerchaintId,TerminalId,lang)
    }


    override suspend fun cancelVisit(
        E_MOVEMNT_SEQNOlbl: String,
        caseidlbl: String,
        Servicelbl_id: String,
        BranchID: String,
        lang: String,
    ): List<SelectDepartment>? {
        return  api.cancelVisit(E_MOVEMNT_SEQNOlbl,caseidlbl,Servicelbl_id,BranchID,lang,)
    }

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