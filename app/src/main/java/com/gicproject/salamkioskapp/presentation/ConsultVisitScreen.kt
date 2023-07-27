package com.gicproject.salamkioskapp.presentation

import android.os.Build
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.gicproject.salamkioskapp.R
import com.gicproject.salamkioskapp.Screen
import com.gicproject.salamkioskapp.common.Constants
import com.gicproject.salamkioskapp.common.Constants.Companion.heartBeatJson
import com.gicproject.salamkioskapp.domain.model.ConsultVisit
import com.gicproject.salamkioskapp.domain.model.Patient
import com.gicproject.salamkioskapp.domain.model.SelectDepartment
import com.gicproject.salamkioskapp.domain.model.SelectService
import com.gicproject.salamkioskapp.ui.theme.DarkGreyText
import com.gicproject.salamkioskapp.ui.theme.LightGreyText
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ConsultVisitScreen(
    selectDepartment: SelectDepartment?,
    patient: Patient?,
    service: SelectService?,
    navController: NavController,
    viewModel: MyViewModel,
    ) {

    val state = viewModel.stateConsultVisitInfo.value
    val context = LocalContext.current
    LaunchedEffect(true) {
        if(state.consultVisit == null){
            Log.d("TAG", "CreateConsultVisit: called ${selectDepartment?.DepartmentPKID} ")
            viewModel.hideBar(context)
            viewModel.onEvent(MyEvent.CreateConsultVisit(selectDepartment,patient,service))
        }


    }

    val second = remember { mutableStateOf(180) }

    if(state.isBack){
        LaunchedEffect(true) {
            navController.popBackStack()

        }
    }
    if(state.isCancel){
        LaunchedEffect(true) {
            navController.popBackStack(Screen.SelectOptionScreen.route, false)

        }
    }

    if (second.value == 0) {

        if(state.consultVisit != null){
            LaunchedEffect(key1 =true){
                viewModel.onEvent(MyEvent.CancelConsultVisit(state.consultVisit, isCancel = true, isBack = false))

            }
        }else{
            LaunchedEffect(key1 =true){
                navController.popBackStack(Screen.SelectOptionScreen.route, false)

            }
        }


    }
    LaunchedEffect(key1 = Unit, block = {
        while (true) {
            delay(1000)
            second.value = second.value - 1

        }
    })
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colors.surface,
                )
        ) {
            Modifier.padding(innerPadding)
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                Image(
                    painter = painterResource(id = Constants.BACKGROUND_IMAGE),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "bg",
                    modifier = Modifier.fillMaxSize()
                )


            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(20.dp)
                ) {
                    if(!state.isLoading){
                        GoBackWithClick(onClick = {
                           if(   state.consultVisit != null){
                                viewModel.onEvent(MyEvent.CancelConsultVisit(state.consultVisit, isCancel = false, isBack = true))
                            }else{
                               navController.popBackStack()
                           }
                        })
                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom
            ) {
                HeartBeatTime(second = second)
            }
            HeaderDesign("Consult Visit","زيارة استشارة",navController)

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                if(!state.isLoading && state.consultVisit != null){
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){

                        Text("${patient?.Patientname}",
                            style = androidx.compose.ui.text.TextStyle(fontFamily = Constants.FontEnglish),
                            fontSize = 30.sp, fontWeight = FontWeight.Bold)

                        Spacer(modifier = Modifier.width(20.dp))


                        Text("${patient?.PatientnameAr}",
                            style = androidx.compose.ui.text.TextStyle(fontFamily = Constants.FontArabic),
                            fontSize = 30.sp, fontWeight = FontWeight.Bold)



                    }
                    Text("Please Confirm the Appointment", fontSize = 25.sp,
                        style = androidx.compose.ui.text.TextStyle(fontFamily = Constants.FontEnglish),)

                    Spacer(modifier = Modifier.height(10.dp))
                    Text("يرجى تأكيد الموعد", fontSize = 25.sp,
                        style = androidx.compose.ui.text.TextStyle(fontFamily = Constants.FontArabic),)


                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                            AppointmentInfoWithConsultVisit(service = service, consultVisit = state.consultVisit)


                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Row() {
                        GreenButton(
                            {
                                    navController.currentBackStackEntry?.savedStateHandle?.set(
                                        Constants.STATE_PATIENT, patient
                                    )
                                    navController.currentBackStackEntry?.savedStateHandle?.set(
                                        Constants.STATE_SELECT_DEPARTMENT, selectDepartment
                                    )

                                    navController.currentBackStackEntry?.savedStateHandle?.set(
                                        Constants.STATE_SERVICE,service
                                    )
                                    navController.navigate(Screen.InsertKnetScreen.route)


                                // viewModel.funcPrinterConnect()
                            },
                            "Ok",
                            textAr = "موافق"
                        )

                        RedButton(
                            {
                                    state.consultVisit.let {
                                        viewModel.onEvent(MyEvent.CancelConsultVisit(state.consultVisit, isCancel = true, isBack = false))

                                    }




                            },
                            "Cancel",

                            textAr = "إلغاء"
                        )
                    }

                }



            }



            /* if (state.error.isNotBlank()) {

             }
             if (state.isLoading) {
                 CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
             }
             if (state.success.isNotBlank()) {
                 LaunchedEffect(key1 = true) {

                 }
             }*/
        }
    }


    if (state.error.isNotBlank()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom,
        ) {
            Text(state.error, color = MaterialTheme.colors.error, fontSize = 24.sp)
        }
    }


    if (state.isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CircularProgressIndicator()
        }
    }
}


@Composable
fun AppointmentInfoWithConsultVisit(service: SelectService?,consultVisit: ConsultVisit?) {
    Box(
        modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            /* Row(
                 modifier = Modifier.fillMaxWidth(),
                 horizontalArrangement = Arrangement.Center,
                 verticalAlignment = Alignment.CenterVertically
             ) {
                 Image(
                     painter = painterResource(id = image),
                     contentDescription = "",
                     modifier = Modifier
                         .width(250.dp)
                         .height(140.dp)
                 )
             }
             Spacer(modifier = Modifier.height(20.dp))*/


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    service?.DOCNAME?.trim() ?: "",
                    color = Color.Black,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    style = androidx.compose.ui.text.TextStyle(fontFamily = Constants.FontEnglish),
                )

                Text(
                    service?.DOCNAMEAR?.trim() ?: "",
                    color = Color.Black,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    style = androidx.compose.ui.text.TextStyle(fontFamily = Constants.FontArabic),
                )


            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    service?.jobtitle  ?: "", color = Color.Black, fontSize = 23.sp,
                    style = androidx.compose.ui.text.TextStyle(fontFamily = Constants.FontEnglish),
                )


                Text(
                    service?.jobtitlear ?: "", color = Color.Black, fontSize = 23.sp,
                    style = androidx.compose.ui.text.TextStyle(fontFamily = Constants.FontArabic),
                )

            }

            Spacer(modifier = Modifier.height(20.dp))


            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    val now = LocalDateTime.now()
                    var currentDate = now?.format(dateFormatter)
                    // Update the time every second
                    Text(
                        "Appointment Date  $currentDate  ",
                        color = DarkGreyText,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        style = androidx.compose.ui.text.TextStyle(fontFamily = Constants.FontEnglish),
                    )
                    Text(
                        "تاريخ الموعد ",
                        color = DarkGreyText,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        style = androidx.compose.ui.text.TextStyle(fontFamily = Constants.FontArabic),
                    )
                }


            }

            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a")
                    val now = LocalDateTime.now()
                    var currentTime = now.format(timeFormatter)
                    // Update the time every second
                    Text(
                        "Appointment Time  $currentTime  ",
                        color = DarkGreyText,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        style = androidx.compose.ui.text.TextStyle(fontFamily = Constants.FontEnglish),
                    )
                    Text(
                        "وقت الموعد ",
                        color = DarkGreyText,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        style = androidx.compose.ui.text.TextStyle(fontFamily = Constants.FontArabic),
                    )
                }


            }

            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

                Text(
                    "Fees  ${consultVisit?.consfees?.toString()} KD ",
                    color = Color.Black,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    style = androidx.compose.ui.text.TextStyle(fontFamily = Constants.FontEnglish),
                )
                Text(
                    "رسوم ",
                    color = Color.Black,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    style = androidx.compose.ui.text.TextStyle(fontFamily = Constants.FontArabic),
                )

            }

            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

                Text(
                    "${consultVisit?.Servicelbl?.toString()}",
                    color = Color.Black,
                    fontSize = 23.sp,
                    style = androidx.compose.ui.text.TextStyle(fontFamily = Constants.FontEnglish, textAlign = TextAlign.Center),
                )

            }

        }
    }
}






