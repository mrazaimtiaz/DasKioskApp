package com.gicproject.salamkioskapp.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
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
import com.gicproject.salamkioskapp.common.Constants.Companion.insertCardJson
import com.gicproject.salamkioskapp.domain.model.ConsultVisit
import com.gicproject.salamkioskapp.domain.model.Patient
import com.gicproject.salamkioskapp.domain.model.SelectDepartment
import com.gicproject.salamkioskapp.domain.model.SelectService
import kotlinx.coroutines.delay
import java.util.*


@Composable
fun InsertKnetScreen(
    selectDepartment: SelectDepartment?,
    patient: Patient?,
    service: SelectService?,
    navController: NavController,
    viewModel: MyViewModel,
) {

    val listState = rememberLazyListState()

    val second = remember { mutableStateOf(180) }

    val state = viewModel.stateConsultVisitInfo.value


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
    if(second.value == 170){
        LaunchedEffect(key1 =true){
            navController.currentBackStackEntry?.savedStateHandle?.set(
                Constants.STATE_PATIENT, patient
            )
            navController.currentBackStackEntry?.savedStateHandle?.set(
                Constants.STATE_SELECT_DEPARTMENT, selectDepartment
            )

            navController.currentBackStackEntry?.savedStateHandle?.set(
                Constants.STATE_SERVICE,service
            )
            navController.navigate(Screen.CreateInvoiceScreen.route)
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
            /*if (second.value == 0) {
                navController.popBackStack(Screen.SelectOptionScreen.route, false)
            }*/
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
                //    GoBack(navController = navController)
                }
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom
            ) {
                HeartBeatTime(second = second)
            }
            HeaderDesign("Insert Knet","أدخل كي نت",navController)



            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

                    Text(
                        "Fees  ${state.consultVisit?.consfees ?: ""} KD ",
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

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    PayKnetAnimation()
                }
                Spacer(modifier = Modifier.height(40.dp))

                RedButton(
                    {
                        if(!state.isLoading){
                            if(state.consultVisit != null){
                                viewModel.onEvent(MyEvent.CancelConsultVisit(state.consultVisit, isCancel = true, isBack = false))

                            }else{

                                navController.popBackStack(Screen.SelectOptionScreen.route, false)
                            }
                        }

                    },
                    "Cancel",

                    textAr = "إلغاء"
                )
               /* SubmitButton({
                    navController.popBackStack(Screen.SelectOptionScreen.route, false)
                }, "Pay","ادفع")*/
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
fun PayKnetAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.JsonString(insertCardJson))
    LottieAnimation(
        composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier.size(400.dp),
        isPlaying = true,

        )
}



