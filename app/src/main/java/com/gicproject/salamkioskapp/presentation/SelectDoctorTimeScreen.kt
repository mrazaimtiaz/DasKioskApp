package com.gicproject.salamkioskapp.presentation

import android.content.res.Resources.Theme
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
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
import com.airbnb.lottie.compose.*
import com.gicproject.salamkioskapp.R
import com.gicproject.salamkioskapp.Screen
import com.gicproject.salamkioskapp.common.Constants
import com.gicproject.salamkioskapp.common.Constants.Companion.heartBeatJson
import com.gicproject.salamkioskapp.ui.theme.primarySidra
import com.google.accompanist.flowlayout.FlowColumn
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.skydoves.balloon.ArrowPositionRules
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.compose.Balloon
import com.skydoves.balloon.compose.BalloonWindow
import com.skydoves.balloon.compose.rememberBalloonBuilder
import kotlinx.coroutines.*
import java.util.*


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SelectDoctorTimeScreen(
    navController: NavController,
    viewModel: MyViewModel,
) {

    val listState = rememberLazyListState()

    val second = remember { mutableStateOf(20) }

    val state = viewModel.stateSelectDoctor.value

    LaunchedEffect(true) {
        viewModel.onEvent(MyEvent.GetDoctor)
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
                        navController.navigate(Screen.SelectDoctorTimeScreen.route)
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
                navController.popBackStack(Screen.SelectDepartmentScreen.route, false)
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
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom
            ) {
                GoBack(navController = navController)
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom
            ) {
                HeartBeatTime(second = second)
            }
            HeaderDesign("Select Doctor","اختر دكتور", navController)

            FlowColumn(
                Modifier.fillMaxSize(),
                crossAxisAlignment = FlowCrossAxisAlignment.Center,
                mainAxisAlignment = FlowMainAxisAlignment.Center,
            ) {
                if (state.error.isNotBlank()) {
                    Text(state.error, color = MaterialTheme.colors.error, fontSize =30.sp, modifier = Modifier.padding(top= 30.dp))
                }
                LazyVerticalGrid(
                    verticalArrangement = Arrangement.Center,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    state = rememberLazyGridState(),
                    contentPadding = PaddingValues(30.dp),
                    modifier = Modifier
                        .width(730.dp)
                        .height(950.dp),
                    columns = GridCells.Fixed(2),
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
                            modifier = Modifier.align(Alignment.Center),
                            builder = builder,
                            balloonContent = {
                                Text(text = "Now you can edit your profile!")
                            }
                        ) { balloonWindow ->
                            DoctorInfoTime(
                                "Dr Emad",
                                "01:00 AM",
                                "01-Nov-2022",
                                "ENT",
                                "30 KD",
                                Constants.DOCTOR_SAMPLE_IMAGE,
                                navController,
                                balloonWindow
                            ) {
                                if (!state.isLoading) {
                                    showDialog.value = true
                                   // navController.navigate(Screen.DoctorPayScreen.route)
                                }
                            }

                        }

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


