package com.gicproject.salamkioskapp.domain.use_case



import com.gicproject.salamkioskapp.common.Constants
import com.gicproject.salamkioskapp.common.Resource
import com.gicproject.salamkioskapp.domain.model.*
import com.gicproject.salamkioskapp.domain.repository.MyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CreateInvoice @Inject constructor(
    private val repository: MyRepository
) {
    operator fun invoke(
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
    ): Flow<Resource<Invoice>> = flow {
        try {
            emit(Resource.Loading())


            val invoices: List<Invoice>? = if (Constants.APPSTATUS == Constants.PRODUCTION) {

                repository.createInvoice(   docsapid,
                    patientid,
                    caseid,
                    sequence,
                    Auth,
                    Cardtype,
                    PntName,
                    BranchID,
                    DocName,
                    transactionno,
                    authcode,
                    cardno,
                    recipts,
                    bankname,
                    MerchaintId,
                    TerminalId,
                    "en",)

            } else {

                listOf(Invoice("9012270365","2023-07-18", "0.0","0026201198","Mohammad Abdullah Hraish Al Enezi","000100", "CB99212A", "Office or other outpatient Visit for an","1.0"
                    ,"0.0", "0.0","1039743698","D-DER","http://192.168.20.160/kisok_web/PrintReport?invoicenumber=9012270365&report=1","",1), )

            }

            if (!invoices.isNullOrEmpty()) {
                emit(Resource.Success(invoices[0]))
            } else {
                emit(Resource.Error("Empty Invoice List."))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}