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

class CheckCivilIDinSAP @Inject constructor(
    private val repository: MyRepository
) {
    operator fun invoke(
         civilId: String,
    ): Flow<Resource<Patient>> = flow {
        try {
            emit(Resource.Loading())

            val patient: List<Patient>? = if (Constants.APPSTATUS == Constants.PRODUCTION) {

                repository.checkCivilIDinSAP(civilId, "en")
            } else {
                listOf(
                    Patient(
                        "Test Patient . Kiosk",
                        ".",
                        "0000366636",
                        "",
                        null,
                        "233333333333",
                        "",
                        1
                    ),
                )
            }


            if (!patient.isNullOrEmpty()) {
                emit(Resource.Success(patient[0]))
            } else {
                emit(Resource.Error("Empty Patient-CheckCivilIDinSAP List."))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}