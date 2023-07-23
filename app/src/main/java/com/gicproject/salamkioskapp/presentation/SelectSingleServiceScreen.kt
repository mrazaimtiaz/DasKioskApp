package com.gicproject.salamkioskapp.presentation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
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
import com.gicproject.salamkioskapp.domain.model.SelectDepartment
import com.gicproject.salamkioskapp.domain.model.SelectService
import com.google.accompanist.flowlayout.FlowColumn
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import kotlinx.coroutines.delay
import java.util.*

import androidx.compose.ui.text.font.GenericFontFamily
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gicproject.salamkioskapp.common.Constants.Companion.SERVICE_HEIGHT
import com.gicproject.salamkioskapp.ui.theme.primarySidra
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SelectSingleServiceScreen(
    selectDepartment: SelectDepartment?,
    navController: NavController,
    viewModel: MyViewModel,
) {

    val listState = rememberLazyListState()

   // val second = remember { mutableStateOf(60) }

    val state = viewModel.stateSelectService.value



    LaunchedEffect(true) {
        while (true) {
            Log.d("TAG", "SelectDepartmentScreen: called GetSelectDepartments" )
            if(!state.isApiLoading){
                viewModel.onEvent(MyEvent.GetSelectServices(selectDepartment?.DepartmentPKID.toString()))

            }
            delay(4000)
        }
    }

/*    LaunchedEffect(true) {

        Log.d(
            "TAG",
            "SelectServiceScreen: department id ${selectDepartment?.DepartmentPKID.toString()}"
        )
        viewModel.onEvent(MyEvent.GetSelectServices(selectDepartment?.DepartmentPKID.toString()))
    }*/
    /*LaunchedEffect(key1 = Unit, block = {
        while (true) {
            delay(1000)
            second.value = second.value - 1
            if (second.value == 0) {
                navController.popBackStack(Screen.SelectDepartmentScreen.route, false)
            }
        }
    })*/
    if (state.success.isNotBlank()) {

        viewModel.readCivilIdOff()
        viewModel.resetInsertCivilIdScreen()
        viewModel.resetServicesScreen()
        viewModel.onEvent(MyEvent.GetSelectServices(selectDepartment?.DepartmentPKID.toString()))
       // navController.popBackStack(Screen.SelectSingleServiceScreen.route, false)
    }
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
                  //  GoBack(navController = navController)
                }
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom
            ) {
             //   HeartBeatTime(second = second)
            }
            HeaderDesign("Select Service","حدد الخدمة", navController)
            if (state.error.isNotBlank()) {
                Text(
                    state.error,
                    color = MaterialTheme.colors.error,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(top = 180.dp)
                )
            }

            FlowColumn(
                Modifier.fillMaxSize(),
                crossAxisAlignment = FlowCrossAxisAlignment.Center,
                mainAxisAlignment = FlowMainAxisAlignment.Center,
            ) {
                LazyVerticalGrid(
                    verticalArrangement = Arrangement.Center,
                    horizontalArrangement = Arrangement.Center,
                    state = rememberLazyGridState(),
                    contentPadding = PaddingValues(70.dp),
                    modifier = Modifier
                        .width(730.dp)
                        .height(SERVICE_HEIGHT),
                    columns = GridCells.Fixed(2),
                ) {
                    items(state.services.size) { index ->

                        if(state.services[index].Logo != null)
                            CustomButton(onClick = {
                                if(!viewModel.stateSelectService.value.isLoading){
                                    viewModel.enableServiceLoading()
                                    CoroutineScope(Dispatchers.Main).launch {
                                        delay(300)
                                        viewModel.selectService = state.services[index]
                                        viewModel.onEvent(
                                            MyEvent.GetBookTicket(
                                                isCivilIdPage = false,
                                                serviceID = state.services[index].ServicesPKID.toString(),
                                                isHandicap = false,
                                                isVip = false,
                                                languageID = "0",
                                                appointmentCode = "-1",
                                                isaapt = false,
                                                refid = "-1",
                                                DoctorServiceID = "-1",
                                                ticketDesignId = state.services[index].doctoridsap.toString()
                                            )
                                        )

                                    }
                                }
                                // showDialog.value = true
                            }, text = state.services[index].ServicesNameEN
                                ?: "" , textAr =
                            state.services[index].ServicesNameAR ?: "",
                            )
                        else
                        ServiceInfo(state.services[index], navController, onClick = {
                            if(!viewModel.stateSelectService.value.isLoading){
                                viewModel.enableServiceLoading()
                                CoroutineScope(Dispatchers.Main).launch {
                                    delay(300)
                                    viewModel.selectService = state.services[index]
                                    viewModel.onEvent(
                                        MyEvent.GetBookTicket(
                                            isCivilIdPage = false,
                                            serviceID = state.services[index].ServicesPKID.toString(),
                                            isHandicap = false,
                                            isVip = false,
                                            languageID = "0",
                                            appointmentCode = "-1",
                                            isaapt = false,
                                            refid = "-1",
                                            DoctorServiceID = "-1",
                                            ticketDesignId = state.services[index].doctoridsap.toString()
                                        )
                                    )

                                }
                            }


                        })

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
}
