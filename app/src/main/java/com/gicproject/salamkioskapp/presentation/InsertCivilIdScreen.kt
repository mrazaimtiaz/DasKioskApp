package com.gicproject.salamkioskapp.presentation

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gicproject.salamkioskapp.R
import com.gicproject.salamkioskapp.Screen
import com.gicproject.salamkioskapp.common.Constants
import kotlinx.coroutines.delay


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InsertCivilIdScreen(
    isService: Boolean?,
    navController: NavController,
    viewModel: MyViewModel,
) {

    val listState = rememberLazyListState()

    val second = remember { mutableStateOf(60) }



    val state = viewModel.stateInsertCivilId.value

    LaunchedEffect(key1 = Unit, block = {
        while (true) {
            delay(1000)
            Log.d("TAG", "InsertCivilIdScreen1: ${state.isLoading}")
            if(!state.isLoading){
                Log.d("TAG", "InsertCivilIdScreen2: ${state.isLoading}")
                second.value = second.value - 1
                if (second.value == 0) {
                    navController.popBackStack(Screen.SelectDepartmentScreen.route, false)
                }
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
            HeartBeatTime(second = second)
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                HeaderDesign("Appointment","موعد",navController)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (state.error.isNotBlank()) {
                        Text(
                            state.error,
                            color = MaterialTheme.colors.error,
                            fontSize = 24.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            textAlign = TextAlign.Center
                        )

                    }
                }
                  // PayKnetAnimation()
                var fontEnglish = FontFamily(Font(R.font.questrial_regular))
                var fontArabic = FontFamily(Font(R.font.ge_dinar_one_medium))
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .weight(5f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                   Column(modifier = Modifier.fillMaxWidth().padding(25.dp)) {
                       Text("Insert Mobile Number or Civil ID of Patient",modifier = Modifier.fillMaxWidth(), fontSize = 27.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, style = TextStyle(fontFamily = fontEnglish))
                       Spacer(modifier = Modifier.height(10.dp))
                       Text("أدخل البطاقة المدنية للمريض أو رقم الهاتف المحمول" , modifier = Modifier.fillMaxWidth(), fontSize = 27.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, style = TextStyle(fontFamily = fontArabic))
                   }

                    Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()){

                        Column( modifier = Modifier
                            .weight(1f)
                            .padding(25.dp),) {

                            Image(
                                painter = painterResource(id = R.drawable.animatedcivilid),
                                modifier = Modifier.size(250.dp),
                                contentDescription = "civilid"
                            )



                        }
                        Column(modifier = Modifier.weight(1f), ){
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .width(250.dp)
                                        .background(
                                            Color.LightGray,
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                ) {
                                    Text(
                                        viewModel.textCivilId.value,
                                        color = Color.Black,
                                        fontSize = 25.sp,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                var context = LocalContext.current
                                Row() {
                                    NumberKeypad({
                                        if (viewModel.textCivilId.value.length != 12) {
                                            viewModel.textCivilId.value = viewModel.textCivilId.value + "1"
                                        } else {
                                            //Toast.makeText(context, "Current Length 12", Toast.LENGTH_SHORT)
                                            //                                   .show()
                                        }
                                    }, "1")
                                    Spacer(modifier = Modifier.width(10.dp))
                                    NumberKeypad({
                                        if (viewModel.textCivilId.value.length != 12) {
                                            viewModel.textCivilId.value = viewModel.textCivilId.value + "2"
                                        } else {
                                            //Toast.makeText(context, "Current Length 12", Toast.LENGTH_SHORT)
                                            //                                   .show()
                                        }
                                    }, "2")
                                    Spacer(modifier = Modifier.width(10.dp))
                                    NumberKeypad({
                                        if (viewModel.textCivilId.value.length != 12) {
                                            viewModel.textCivilId.value = viewModel.textCivilId.value + "3"
                                        } else {
                                            //Toast.makeText(context, "Current Length 12", Toast.LENGTH_SHORT)
                                            //                                   .show()
                                        }
                                    }, "3")
                                    Spacer(modifier = Modifier.width(10.dp))
                                }
                                Spacer(modifier = Modifier.height(5.dp))
                                Row() {
                                    NumberKeypad({
                                        if (viewModel.textCivilId.value.length != 12) {
                                            viewModel.textCivilId.value = viewModel.textCivilId.value + "4"
                                        } else {
                                            //Toast.makeText(context, "Current Length 12", Toast.LENGTH_SHORT)
                                            //                                   .show()
                                        }
                                    }, "4")
                                    Spacer(modifier = Modifier.width(10.dp))
                                    NumberKeypad({
                                        if (viewModel.textCivilId.value.length != 12) {
                                            viewModel.textCivilId.value = viewModel.textCivilId.value + "5"
                                        } else {
                                            //Toast.makeText(context, "Current Length 12", Toast.LENGTH_SHORT)
                                            //                                   .show()
                                        }
                                    }, "5")
                                    Spacer(modifier = Modifier.width(10.dp))
                                    NumberKeypad({
                                        if (viewModel.textCivilId.value.length != 12) {
                                            viewModel.textCivilId.value = viewModel.textCivilId.value + "6"
                                        } else {
                                            //Toast.makeText(context, "Current Length 12", Toast.LENGTH_SHORT)
                                            //                                   .show()
                                        }
                                    }, "6")
                                    Spacer(modifier = Modifier.width(10.dp))
                                }
                                Spacer(modifier = Modifier.height(5.dp))
                                Row() {
                                    NumberKeypad({
                                        if (viewModel.textCivilId.value.length != 12) {
                                            viewModel.textCivilId.value = viewModel.textCivilId.value + "7"
                                        } else {
                                            //Toast.makeText(context, "Current Length 12", Toast.LENGTH_SHORT)
                                            //                                   .show()
                                        }
                                    }, "7")
                                    Spacer(modifier = Modifier.width(10.dp))
                                    NumberKeypad({
                                        if (viewModel.textCivilId.value.length != 12) {
                                            viewModel.textCivilId.value = viewModel.textCivilId.value + "8"
                                        } else {
                                            //Toast.makeText(context, "Current Length 12", Toast.LENGTH_SHORT)
                                            //                                   .show()
                                        }
                                    }, "8")
                                    Spacer(modifier = Modifier.width(10.dp))
                                    NumberKeypad({
                                        if (viewModel.textCivilId.value.length != 12) {
                                            viewModel.textCivilId.value = viewModel.textCivilId.value + "9"
                                        } else {
                                            //Toast.makeText(context, "Current Length 12", Toast.LENGTH_SHORT)
                                            //                                   .show()
                                        }
                                    }, "9")
                                    Spacer(modifier = Modifier.width(10.dp))
                                }
                                Spacer(modifier = Modifier.height(5.dp))
                                Row() {
                                    NumberKeypad({
                                        viewModel.textCivilId.value = ""
                                    }, isIconClose = true)
                                    Spacer(modifier = Modifier.width(10.dp))
                                    NumberKeypad({
                                        if (viewModel.textCivilId.value.length != 12) {
                                            viewModel.textCivilId.value = viewModel.textCivilId.value + "0"
                                        } else {
                                            //Toast.makeText(context, "Current Length 12", Toast.LENGTH_SHORT)
                                            //                                   .show()
                                        }
                                    }, "0")
                                    Spacer(modifier = Modifier.width(10.dp))
                                    NumberKeypad({
                                        if (viewModel.textCivilId.value.isNotBlank()) {
                                            viewModel.textCivilId.value =
                                                viewModel.textCivilId.value.substring(0, viewModel.textCivilId.value.length - 1);
                                        }

                                    }, isIconBack = true)
                                    Spacer(modifier = Modifier.width(10.dp))
                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Button(
                                        onClick = {
                                            viewModel.onEvent(MyEvent.GetCivilIdAppointment(viewModel.textCivilId.value))
                                        },
                                        modifier = Modifier
                                            .padding(vertical = 20.dp)
                                            .shadow(50.dp, shape = RoundedCornerShape(5.dp)),
                                        shape = RoundedCornerShape(10.dp)
                                    ) {
                                        Spacer(modifier = Modifier.width(20.dp))
                                        Icon(
                                            Icons.Default.Send,
                                            contentDescription = "",
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Row() {
                                            Text("Submit  ", fontSize = 20.sp, fontFamily = fontEnglish, fontWeight = FontWeight.Bold)
                                            Text("ارسل", fontSize = 20.sp, fontFamily = fontArabic, fontWeight = FontWeight.Bold)

                                        }
                                        Spacer(modifier = Modifier.width(10.dp))
                                    }
                                }
                            }
                        }
                    }
                }

                //divider section
               /* Row() {
                    Column(modifier = Modifier.weight(2f),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Divider()

                    }
                   *//* Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Row() {
                            Text("OR  ", fontSize = 35.sp, fontFamily = fontEnglish, fontWeight = FontWeight.Bold)
                            Text("أو", fontSize = 35.sp, fontFamily = fontArabic, fontWeight = FontWeight.Bold)

                        }

                    }*//*
                    Column(modifier = Modifier.weight(2f),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Divider()

                    }

                }*/
                //bottom section
            /*    Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.weight(4f)) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .padding(15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Insert Patient Civil ID to the \n Machine",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start,
                            style = TextStyle(fontFamily = fontEnglish)
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            "أدخل الهوية المدنية للمريض على \nالجهاز",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End,
                            style = TextStyle(fontFamily = fontArabic)
                        )

                    }
                    Column(  modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .padding(15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {
                            Image(
                                painter = painterResource(id = R.drawable.animatedcivilid),
                                modifier = Modifier.size(200.dp),
                                contentDescription = "civilid"
                            )
                    }
                }*/


                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,

                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth()
                    ) {
                        GoBack(navController)
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

        if (state.success.isNotBlank()) {
            navController.popBackStack(Screen.SelectDepartmentScreen.route, false)
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


@Composable
fun NumberKeypad(
    onClick: () -> Unit,
    text: String = "",
    isIconClose: Boolean = false,
    isIconBack: Boolean = false
) {
    Button(
        onClick = onClick,
        colors = if (isIconClose || isIconBack) {
            ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
        } else {
            ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
        },
        modifier = Modifier
            .width(48.dp)
            .height(48.dp)
            .shadow(10.dp, shape = RoundedCornerShape(15.dp)),
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isIconClose) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "",
                    tint = Color.Red,
                    modifier = Modifier.size(70.dp)
                )
            } else if (isIconBack) {
                Icon(
                    Icons.Default.KeyboardArrowLeft,
                    contentDescription = "",
                    tint = Color.Yellow,
                    modifier = Modifier.size(70.dp)
                )
            } else {

                Text(
                    text, fontSize = 20.sp,  textAlign = TextAlign.Center, color = Color.White
                )
            }
        }

    }
}

