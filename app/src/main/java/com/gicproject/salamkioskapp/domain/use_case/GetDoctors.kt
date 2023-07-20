package com.gicproject.salamkioskapp.domain.use_case


import com.gicproject.salamkioskapp.common.Resource
import com.gicproject.salamkioskapp.domain.model.Department
import com.gicproject.salamkioskapp.domain.model.Doctor
import com.gicproject.salamkioskapp.domain.repository.MyRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDoctors @Inject constructor(
    private val repository: MyRepository
) {
    operator fun invoke(branchId: String,parentId: String): Flow<Resource<List<Doctor>>> = flow {
        try {
            emit(Resource.Loading())

       //   var doctors = repository.getDoctorsWithTime(branchId,parentId)
            var doctors = listOf(Doctor("1","Dr Emad Al Hamar","Head Surgen - Dar ul Shifa","30 KD","01:00 PM - 01:30 PM","03-02-2023"),)

            delay(500)
            if (!doctors.isNullOrEmpty()) {
                emit(Resource.Success(doctors.map {
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