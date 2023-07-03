package com.gicproject.salamkioskapp.presentation

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.gicproject.salamkioskapp.R
import com.gicproject.salamkioskapp.Screen
import com.gicproject.salamkioskapp.common.Constants
import com.gicproject.salamkioskapp.domain.model.SelectDepartment
import com.gicproject.salamkioskapp.ui.theme.DarkBlueText
import com.gicproject.salamkioskapp.ui.theme.DarkGreyText
import com.gicproject.salamkioskapp.ui.theme.LightGreyText
import com.gicproject.salamkioskapp.ui.theme.primarySidra
import com.google.accompanist.flowlayout.FlowColumn
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.skydoves.balloon.ArrowPositionRules
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.compose.Balloon
import com.skydoves.balloon.compose.BalloonWindow
import com.skydoves.balloon.compose.rememberBalloonBuilder
import kotlinx.coroutines.*
import java.time.format.TextStyle


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SelectDoctorScreen(
    selectDepartment: SelectDepartment?,
    navController: NavController,
    viewModel: MyViewModel,
) {

    val listState = rememberLazyListState()

    val second = remember { mutableStateOf(180) }

    val state = viewModel.stateSelectDoctor.value

    LaunchedEffect(true) {
        Log.d("TAG", "SelectDoctorScreen: called ${selectDepartment?.DepartmentPKID} ")
        viewModel.onEvent(MyEvent.GetDoctor(selectDepartment?.DepartmentPKID.toString()))
    }

    var showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        /*val second = remember { mutableStateOf(30) }
        LaunchedEffect(key1 = Unit, block = {
            while (true) {
                delay(1000)
                second.value = second.value - 1
                if (second.value == 0) {
                    showDialog.value = false
                }
            }
        })*/
        Dialog(
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = false
            ),
            onDismissRequest = {
                showDialog.value = false
            },

            ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(top = 80.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CustomButton(onClick = {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            Constants.STATE_EXTRA, false
                        )
                        navController.navigate(Screen.InsertCivilIdScreen.route)
                    }, text = "Appointment","موعد")
                    CustomButton(onClick = {
                        navController.navigate(Screen.SelectDoctorScreen.route)
                    }, text = "Without Appointment","بدون موعد")
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row() {
                    Button(
                        onClick = { showDialog.value = false },
                        modifier = Modifier
                            .padding(20.dp)
                            .shadow(50.dp, shape = RoundedCornerShape(5.dp)),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
                    ) {
                        Icon(
                            Icons.Default.KeyboardArrowLeft,
                            contentDescription = "",
                            modifier = Modifier.size(50.dp)
                        )

                        Spacer(modifier = Modifier.width(20.dp))
                        val fontEnglish = FontFamily(Font(R.font.questrial_regular))
                        val fontArabic = FontFamily(Font(R.font.ge_dinar_one_medium))
                        Row(){
                            Text("Back  ", fontSize = 25.sp, fontFamily = fontEnglish)
                            Text("عوده", fontSize = 25.sp, fontFamily = fontArabic)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                   // HeartBeatTimeRow(second = second)
                }
            }
        }
    }



    LaunchedEffect(key1 = Unit, block = {
        while (true) {
            delay(1000)
            second.value = second.value - 1
            if (second.value == 0) {
                navController.popBackStack(Screen.SelectOptionScreen.route, false)
            }
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
                modifier = Modifier.fillMaxSize()
            ) {

                HeaderDesign("Select Doctor","اختر دكتور", navController)

                    if (state.error.isNotBlank()) {
                        Text(state.error, color = MaterialTheme.colors.error, fontSize =30.sp, modifier = Modifier.padding(top= 30.dp))
                    }
                  /*  LazyVerticalGrid(
                            verticalArrangement = Arrangement.Center,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            state = rememberLazyGridState(),
                            contentPadding = PaddingValues(horizontal = 30.dp, vertical = 50.dp),
                            columns = GridCells.Fixed(1),
                    ) {*/

                        LazyColumn(
                            //.fillMaxSize()
                         modifier = Modifier.padding(end = 30.dp, start = 30.dp, bottom = 90.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                        items(state.doctors.size) { index ->
                            val builder = rememberBalloonBuilder {
                                setArrowSize(10)
                                setArrowPosition(0.5f)
                                setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
                                setWidth(BalloonSizeSpec.WRAP)
                                setHeight(BalloonSizeSpec.WRAP)
                                setPadding(12)
                                setMarginHorizontal(12)
                                setCornerRadius(8f)
                                setBackgroundColorResource(R.color.greyKcb)
                                setBalloonAnimation(BalloonAnimation.ELASTIC)
                            }
                            Balloon(
                                // modifier = Modifier.align(Alignment.Center),
                                builder = builder,
                                balloonContent = {
                                    Text(text = "information!", color = Color.White)
                                }
                            ) { balloonWindow ->
                                DoctorInfoTimeNew(
                                    state.doctors[index].nameEn ?: "",
                                    state.doctors[index].time ?: "",
                                    state.doctors[index].date ?: "",
                                    state.doctors[index].departmentEn ?: "",
                                    state.doctors[index].price ?: "",
                                    Constants.DOCTOR_SAMPLE_IMAGE,
                                    navController,
                                    balloonWindow
                                ) {
                                    if (!state.isLoading) {
                                        navController.navigate(Screen.DoctorPayScreen.route)
                                    }
                                }

                            }

                            Spacer(modifier = Modifier.height(15.dp))

                        }
                    }



            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 5.dp)
                        .fillMaxWidth()
                ) {
                    GoBack(navController = navController)

                    HeartBeatTime(second = second)

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
}

var jobBallon: Job? = null;

@Composable
fun DoctorInfoTime(
    name: String,
    time: String,
    date: String,
    deptName: String,
    price: String,
    image: Int,
    navController: NavController,
    ballonWindow: BalloonWindow,
    onClick: () -> Unit,
) {

    ConstraintLayout {
        val someImage = createRef()

        Column(modifier = Modifier.constrainAs(someImage) {
            // Place it where you want
        }) {
            Box(
                modifier = Modifier
                    .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                    .border(
                        width = 2.dp,
                        color = primarySidra,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 12.dp)
                    .clickable {
                        onClick()
                    }, contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.width(300.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
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
                    Spacer(modifier = Modifier.height(20.dp))
                    IconButton(onClick = {
                        jobBallon?.cancel()
                        ballonWindow.showAlignTop()
                        jobBallon = CoroutineScope(Dispatchers.Main).launch {
                            delay(3000)
                            ballonWindow.dismiss()
                        }
                    }) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = "",
                            modifier = Modifier.size(25.dp)
                        )

                    }
                    Text(
                        "Name: $name",
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Text("Department: $deptName", color = Color.Black, fontSize = 28.sp)

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Price: $price",
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }


}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DoctorInfoTimeNew(
    name: String,
    time: String,
    date: String,
    deptName: String,
    price: String,
    image: Int,
    navController: NavController,
    ballonWindow: BalloonWindow,
    onClick: () -> Unit,
) {


    var fontEnglish = FontFamily(Font(R.font.questrial_regular))
    var fontArabic = FontFamily(Font(R.font.ge_dinar_one_medium))
    
    Card(elevation = 4.dp,
        shape = RoundedCornerShape(25.dp)) {
        Spacer(modifier = Modifier.width(5.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }, )
        {
            Column() {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "",
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                    Text(
                        "$name",
                        style = androidx.compose.ui.text.TextStyle(fontFamily = fontEnglish),
                        color = DarkBlueText,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(onClick = {
                        jobBallon?.cancel()
                        ballonWindow.showAlignTop()
                        jobBallon = CoroutineScope(Dispatchers.Main).launch {
                            delay(3000)
                            ballonWindow.dismiss()
                        }
                    }) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = "",
                            modifier = Modifier.size(25.dp)
                        )

                    }
                }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text("$deptName",
                        style = androidx.compose.ui.text.TextStyle(fontFamily = fontEnglish),
                        color = LightGreyText, fontSize = 22.sp)

                Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        "$time",
                        color = DarkGreyText,
                        style = androidx.compose.ui.text.TextStyle(fontFamily = fontEnglish),
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold
                    )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                    Text(
                        "$price",
                        style = androidx.compose.ui.text.TextStyle(fontFamily = fontEnglish,
                            color = LightGreyText,),
                        color = Color.LightGray,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold
                    )


                    Chip(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        colors = ChipDefaults.chipColors(backgroundColor = DarkBlueText),
                        onClick = onClick) {
                        Text("Appointment", color = Color.White)

                    }

                }


            }
            
        }
    }




}


