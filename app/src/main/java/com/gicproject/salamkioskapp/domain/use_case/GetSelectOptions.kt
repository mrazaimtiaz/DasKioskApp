package com.gicproject.salamkioskapp.domain.use_case


import com.gicproject.salamkioskapp.common.Resource
import com.gicproject.salamkioskapp.domain.model.Department
import com.gicproject.salamkioskapp.domain.model.SelectDepartment
import com.gicproject.salamkioskapp.domain.model.SelectOption
import com.gicproject.salamkioskapp.domain.repository.MyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSelectOptions @Inject constructor(
    private val repository: MyRepository
) {
    operator fun invoke(
        branchId: String,
        deptParentId: String): Flow<Resource<List<SelectOption>>> = flow {
        try {
            emit(Resource.Loading())

          // var selectDepartments = repository.getOptions(branchId,deptParentId)

          var selectDepartments = listOf(SelectOption(1,"Consultation Visit","زيارة استشارية"),SelectOption(2,"Services","خدمات"),)
            if (!selectDepartments.isNullOrEmpty()) {
                emit(Resource.Success(selectDepartments.map {
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