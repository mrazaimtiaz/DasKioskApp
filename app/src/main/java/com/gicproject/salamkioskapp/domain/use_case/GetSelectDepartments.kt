package com.gicproject.salamkioskapp.domain.use_case


import com.gicproject.salamkioskapp.common.Resource
import com.gicproject.salamkioskapp.domain.model.Department
import com.gicproject.salamkioskapp.domain.model.SelectDepartment
import com.gicproject.salamkioskapp.domain.repository.MyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSelectDepartments @Inject constructor(
    private val repository: MyRepository
) {
    operator fun invoke(
        branchId: String,
        deptParentId: String): Flow<Resource<List<SelectDepartment>>> = flow {
        try {
            emit(Resource.Loading())

         //  var selectDepartments = repository.getSelectDepartments(branchId,deptParentId)

            var selectDepartments = listOf(SelectDepartment(1,"Surgery Department"),SelectDepartment(1,"Surgery Department"))
            if (!selectDepartments.isNullOrEmpty()) {
                emit(Resource.Success((selectDepartments).map {
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