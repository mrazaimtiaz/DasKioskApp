package com.gicproject.salamkioskapp.domain.use_case



import com.gicproject.salamkioskapp.common.Constants
import com.gicproject.salamkioskapp.common.Resource
import com.gicproject.salamkioskapp.domain.model.Department
import com.gicproject.salamkioskapp.domain.model.Patient
import com.gicproject.salamkioskapp.domain.model.SelectDepartment
import com.gicproject.salamkioskapp.domain.repository.MyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CancelVisit @Inject constructor(
    private val repository: MyRepository
) {
    operator fun invoke(
        E_MOVEMNT_SEQNOlbl: String,
        caseidlbl: String,
        Servicelbl_id: String,
        BranchID: String,
    ): Flow<Resource<SelectDepartment>> = flow {
        try {
            emit(Resource.Loading())

            val patient: List<SelectDepartment>? = if (Constants.APPSTATUS == Constants.PRODUCTION) {

                repository.cancelVisit( E_MOVEMNT_SEQNOlbl,
                    caseidlbl,
                    Servicelbl_id,
                    BranchID,"en")
            } else {
                listOf(
                    SelectDepartment(
                        0,
                        null,
                        null,
                        1,
                        null,
                        null,
                        "Cancelled Successfully",
                    ),
                )
            }


            if (!patient.isNullOrEmpty()) {
                emit(Resource.Success(patient[0]))
            } else {
                emit(Resource.Error("Empty CancelVisit List."))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}