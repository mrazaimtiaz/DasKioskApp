package com.gicproject.salamkioskapp.domain.use_case


import com.gicproject.salamkioskapp.common.Resource
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
        civilId: String,): Flow<Resource<SelectService>> = flow {
        try {
            emit(Resource.Loading())
              var selectServices =  listOf(SelectServiceDto(ServicesPKID=1,
                    ServicesNameEN = "Dr.Mohammad Motaz Varabi",
                   ServicesNameAR = "د.محمد معتز فارابي",
                   ServicesDescription = "Description is here onte",
                   ServicesDescriptionAr = "د.محمد معتز فارابيddsd "
                ),SelectServiceDto(ServicesPKID=1,
                    ServicesNameEN = "Medical Department Two"
                ),SelectServiceDto(ServicesPKID=1,
                   ServicesNameEN = "Dr.Mohammad Motaz Varabi",
                   ServicesNameAR = "د.محمد معتز فارابي",
                   ServicesDescription = "Description is here onte",
                   ServicesDescriptionAr = "د.محمد معتز فارابيddsd "
               ),SelectServiceDto(ServicesPKID=1,
                   ServicesNameEN = "Dr.Mohammad Motaz Varabi",
                   ServicesNameAR = "د.محمد معتز فارابي",
                   ServicesDescription = "Description is here onte",
                   ServicesDescriptionAr = "د.محمد معتز فارابيddsd "
               ),SelectServiceDto(ServicesPKID=1,
                   ServicesNameEN = "Dr.Mohammad Motaz Varabi",
                   ServicesNameAR = "د.محمد معتز فارابي",
                   ServicesDescription = "Description is here onte",
                   ServicesDescriptionAr = "د.محمد معتز فارابيddsd "
               ),SelectServiceDto(ServicesPKID=1,
                   ServicesNameEN = "Dr.Mohammad Motaz Varabi",
                   ServicesNameAR = "د.محمد معتز فارابي",
                   ServicesDescription = "Description is here onte",
                   ServicesDescriptionAr = "د.محمد معتز فارابيddsd "
               ),SelectServiceDto(ServicesPKID=1,
                   ServicesNameEN = "Dr.Mohammad Motaz Varabi",
                   ServicesNameAR = "د.محمد معتز فارابي",
                   ServicesDescription = "Description is here onte",
                   ServicesDescriptionAr = "د.محمد معتز فارابيddsd "
               ),SelectServiceDto(ServicesPKID=1,
                   ServicesNameEN = "Dr.Mohammad Motaz Varabi",
                   ServicesNameAR = "د.محمد معتز فارابي",
                   ServicesDescription = "Description is here onte",
                   ServicesDescriptionAr = "د.محمد معتز فارابيddsd "
               ),SelectServiceDto(ServicesPKID=1,
                   ServicesNameEN = "Dr.Mohammad Motaz Varabi",
                   ServicesNameAR = "د.محمد معتز فارابي",
                   ServicesDescription = "Description is here onte",
                   ServicesDescriptionAr = "د.محمد معتز فارابيddsd "
               ))

          // var selectServices = repository.getCivilIdAppointment(civilId)
            if (!selectServices.isNullOrEmpty()) {
                emit(Resource.Success(  selectServices[0].toSelectService()))
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