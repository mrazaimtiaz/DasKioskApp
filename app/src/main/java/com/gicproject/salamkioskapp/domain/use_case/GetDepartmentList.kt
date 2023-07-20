package com.gicproject.salamkioskapp.domain.use_case



import com.gicproject.salamkioskapp.common.Constants.Companion.APPSTATUS
import com.gicproject.salamkioskapp.common.Constants.Companion.PRODUCTION
import com.gicproject.salamkioskapp.common.Resource
import com.gicproject.salamkioskapp.domain.model.Department
import com.gicproject.salamkioskapp.domain.model.SelectDepartment
import com.gicproject.salamkioskapp.domain.repository.MyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDepartmentList @Inject constructor(
    private val repository: MyRepository
) {
    operator fun invoke(
         branchId: String,
    ): Flow<Resource<List<SelectDepartment>>> = flow {
        try {
            emit(Resource.Loading())

            val departments :List<SelectDepartment>? = if(APPSTATUS == PRODUCTION){

               repository.getDepartmentList(branchId,"en")
           }else{
               listOf(SelectDepartment(2,"Dermatology Dept", "قسم الجلدية",1,"","D-DER",""),)
           }



            if (!departments.isNullOrEmpty()) {
                emit(Resource.Success(departments.map {
                    it
                }))
            } else {
                emit(Resource.Error("Empty Department List."))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}