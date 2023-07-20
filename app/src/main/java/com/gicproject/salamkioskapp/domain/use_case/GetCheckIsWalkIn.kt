package com.gicproject.salamkioskapp.domain.use_case


import com.gicproject.salamkioskapp.common.Resource
import com.gicproject.salamkioskapp.domain.model.CheckIsWalkIn
import com.gicproject.salamkioskapp.domain.repository.MyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCheckIsWalkIn @Inject constructor(
    private val repository: MyRepository
) {
    operator fun invoke(
        serviceId: String,): Flow<Resource<CheckIsWalkIn>> = flow {
        try {
            emit(Resource.Loading())
           //   var checkIsWalkIns =  listOf(CheckIsWalkIn(true))

           var checkIsWalkIns = repository.checkIsWalkIn(serviceId)
            if (!checkIsWalkIns.isNullOrEmpty()) {
                emit(Resource.Success(  checkIsWalkIns[0]))
            } else {
                emit(Resource.Error("Empty CheckWalkIn List."))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}