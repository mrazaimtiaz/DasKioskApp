package com.gicproject.salamkioskapp.domain.use_case



import com.gicproject.salamkioskapp.common.Resource
import com.gicproject.salamkioskapp.domain.model.SelectService
import com.gicproject.salamkioskapp.domain.repository.MyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDoctorList @Inject constructor(
    private val repository: MyRepository
) {
    operator fun invoke(
        deptId: String,
    ): Flow<Resource<List<SelectService>>> = flow {
        try {
            emit(Resource.Loading())

           val doctors = repository.getdoctorlist(deptId,"en")


         // var doctors = listOf(SelectService(0,null,null,0,"0",null,null,0, 30.000, "Dr. Mohammed Al Enezi",
         //     null, "","د. محمد العنزي",null, "0000300417","OC-DERM",null,"0000300417",
         //     "إستشاري جلدية وتناسلية", "Consultant-Dermatology","0000300417","OC-DERM","D-DER"))

            if (!doctors.isNullOrEmpty()) {
                emit(Resource.Success(doctors.map {
                    it
                }))
            } else {
                emit(Resource.Error("Empty Doctors List."))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}