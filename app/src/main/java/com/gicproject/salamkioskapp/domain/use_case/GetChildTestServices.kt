package com.gicproject.salamkioskapp.domain.use_case


import com.gicproject.salamkioskapp.Screen
import com.gicproject.salamkioskapp.common.Resource
import com.gicproject.salamkioskapp.data.remote.dto.DepartmentDto
import com.gicproject.salamkioskapp.data.remote.dto.SelectDepartmentDto
import com.gicproject.salamkioskapp.data.remote.dto.SelectOptionDto
import com.gicproject.salamkioskapp.data.remote.dto.SelectServiceDto
import com.gicproject.salamkioskapp.domain.model.Department
import com.gicproject.salamkioskapp.domain.model.SelectDepartment
import com.gicproject.salamkioskapp.domain.model.SelectOption
import com.gicproject.salamkioskapp.domain.model.SelectService
import com.gicproject.salamkioskapp.domain.repository.MyRepository
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

          var selectTestServices = listOf(SelectServiceDto("Test1","Test1",1,ServicesParentID = 15),SelectServiceDto("Test1","Test1",1,ServicesParentID = 15),SelectServiceDto("Test1","Test1",1,ServicesParentID = 15),SelectServiceDto("Test1","Test1",1,ServicesParentID = 15),SelectServiceDto("Test1","Test1",1,ServicesParentID = 15),SelectServiceDto("Test1","Test1",1,ServicesParentID = 15),SelectServiceDto("Test1","Test1",1,ServicesParentID = 15),SelectServiceDto("Test1","Test1",1,ServicesParentID = 15),SelectServiceDto("Test1","Test1",1,ServicesParentID = 15),SelectServiceDto("Test1","Test1",1,ServicesParentID = 15),SelectServiceDto("Test1","Test1",1,ServicesParentID = 15),SelectServiceDto("Test1","Test1",1,ServicesParentID = 15),SelectServiceDto("Test1","Test1",1,ServicesParentID = 15),SelectServiceDto("Test1","Test1",1,ServicesParentID = 15),SelectServiceDto("Test1","Test1",1,ServicesParentID = 15),SelectServiceDto("Test2","Test2",2,ServicesParentID = 10),SelectServiceDto("Test3","Test3",3,ServicesParentID = 12),SelectServiceDto("Test4","Test4",4,ServicesParentID = 7),)
            if (!selectTestServices.isNullOrEmpty()) {
                emit(Resource.Success(selectTestServices.map {
                    it.toSelectService()
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