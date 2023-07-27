package com.gicproject.salamkioskapp.domain.use_case



import com.gicproject.salamkioskapp.common.Constants
import com.gicproject.salamkioskapp.common.Resource
import com.gicproject.salamkioskapp.domain.model.*
import com.gicproject.salamkioskapp.domain.repository.MyRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CreateConsultVisit @Inject constructor(
    private val repository: MyRepository
) {
    operator fun invoke(
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
    ): Flow<Resource<ConsultVisit>> = flow {
        try {
            emit(Resource.Loading())


            val consultVisit: List<ConsultVisit>? = if (Constants.APPSTATUS == Constants.PRODUCTION) {

                repository.createConsultVisit(  patientid,
                    department,
                    nurseou,
                    att_phys,
                    appidlbl,
                    CIVILID,
                    PntName,
                    pntnamear,
                    TerminalId,
                    BranchID,"en")

            } else {
                delay(2000)
                listOf(ConsultVisit("00001","0.0", "0000366636","233333333333","Test Patient . Kiosk","0000300417", "", "0026201198","Office or other outpatient Visit for an established Patient,Follow Up Within A W","CB99212A", "",1), )

            }

            if (!consultVisit.isNullOrEmpty()) {
                emit(Resource.Success(consultVisit[0]))
            } else {
                emit(Resource.Error("Empty ConsultVisit List."))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}