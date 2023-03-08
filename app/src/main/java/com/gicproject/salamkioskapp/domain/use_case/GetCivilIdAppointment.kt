package com.gicproject.salamkioskapp.domain.use_case


import com.gicproject.salamkioskapp.common.Resource
import com.gicproject.salamkioskapp.data.remote.dto.CheckAppt
import com.gicproject.salamkioskapp.data.remote.dto.DepartmentDto
import com.gicproject.salamkioskapp.data.remote.dto.SelectDepartmentDto
import com.gicproject.salamkioskapp.data.remote.dto.SelectServiceDto
import com.gicproject.salamkioskapp.domain.model.Department
import com.gicproject.salamkioskapp.domain.model.SelectDepartment
import com.gicproject.salamkioskapp.domain.model.SelectService
import com.gicproject.salamkioskapp.domain.repository.MyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCivilIdAppointment @Inject constructor(
    private val repository: MyRepository
) {
    operator fun invoke(
        civilId: String,serviceId: String): Flow<Resource<CheckAppt>> = flow {
        try {
            emit(Resource.Loading())
           //   var checkAppt =  listOf(CheckAppt(ApptExist = true))

           var checkAppts = repository.getCivilIdAppointment(civilId, serviceId = serviceId)
            if (!checkAppts.isNullOrEmpty()) {
                emit(Resource.Success(  checkAppts[0]))
            } else {
                emit(Resource.Error("Empty Check Appts List."))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}