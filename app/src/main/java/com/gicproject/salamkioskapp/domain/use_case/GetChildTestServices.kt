package com.gicproject.salamkioskapp.domain.use_case


import com.gicproject.salamkioskapp.Screen
import com.gicproject.salamkioskapp.common.Resource
import com.gicproject.salamkioskapp.domain.model.Department
import com.gicproject.salamkioskapp.domain.model.SelectDepartment
import com.gicproject.salamkioskapp.domain.model.SelectOption
import com.gicproject.salamkioskapp.domain.model.SelectService
import com.gicproject.salamkioskapp.domain.repository.MyRepository
import com.gicproject.salamkioskapp.emvnfccard.model.Service
import com.gicproject.salamkioskapp.presentation.CustomButton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetChildTestServices @Inject constructor(
    private val repository: MyRepository
) {
    operator fun invoke(
        branchId: String,
        deptParentId: String): Flow<Resource<List<SelectService>>> = flow {
        try {
            emit(Resource.Loading())

        //  var selectTestServices= repository.getSelectTestService(branchId,deptParentId)

          var selectTestServices:  List<SelectService> = listOf()
            if (!selectTestServices.isNullOrEmpty()) {
                emit(Resource.Success(selectTestServices.map {
                    it
                }))
            } else {
                emit(Resource.Error("Empty Service List."))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}