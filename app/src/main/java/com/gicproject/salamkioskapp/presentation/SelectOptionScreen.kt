package com.gicproject.salamkioskapp.presentation

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.gicproject.salamkioskapp.R
import com.gicproject.salamkioskapp.Screen
import com.gicproject.salamkioskapp.common.Constants
import com.gicproject.salamkioskapp.common.Constants.Companion.OPTION_WIDTH
import com.gicproject.salamkioskapp.ui.theme.primarySidra
import kotlinx.coroutines.delay

@Composable
fun SelectOptionScreen(
    navController: NavController,
    viewModel: MyViewModel,
) {

    val state = viewModel.stateSelectOption.value;


    LaunchedEffect(true) {
          while (true) {
              Log.d("TAG", "SelectDepartmentScreen: called GetSelectDepartments" )
              if(!state.isApiLoading){
                  viewModel.onEvent(MyEvent.GetSelectOptions)

              }
              delay(4000)
          }
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
                modifier = Modifier.fillMaxSize()
            ) {

                HeaderDesign("Select Option","حدد خيار", navController)
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    items(state.options.size) { index ->
                        CustomButtonLarge(onClick = {
                            if(state.options[index].DepartmentPKID == 2){

                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    Constants.STATE_EXTRA, true
                                )

                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    Constants.STATE_IS_APPOINTMENT, true
                                )
                                navController.navigate(Screen.InsertCivilIdScreen.route)
                            }else{
                                navController.navigate(Screen.SelectDepartmentScreen.route)

                            }
                            //  navController.navigate(Screen.SelectTestServiceScreen.route)
                        }, textEn = state.options[index].DepartmentNameEN ?: "" , textAr =state.options[index].DepartmentNameAR ?: "" )
                        Spacer(modifier = Modifier.height(40.dp))
                    }


                }
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
}

@Composable
fun CustomButtonLarge(onClick: () -> Unit, textEn: String,textAr: String,) {


    Button(
        onClick = onClick,
        modifier = Modifier
            .disableSplitMotionEvents()
            .width(480.dp)
            .height(140.dp)
            .shadow(50.dp, shape = RoundedCornerShape(5.dp)), shape = RoundedCornerShape(30.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                textEn,
                style = TextStyle(fontFamily =Constants.FontEnglish),
                fontSize = 40.sp,
                textAlign = TextAlign.Center
            )
            Text(
                textAr,
                style = TextStyle(fontFamily =Constants.FontArabic),
                fontSize = 40.sp,
                textAlign = TextAlign.Center
            )
        }

    }
}

@Composable
fun CustomButton(onClick: () -> Unit, text: String,textAr: String) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(contentColor = primarySidra),
        border = BorderStroke(1.dp, primarySidra),
        modifier = Modifier
            .disableSplitMotionEvents()
            .width(260.dp)
            .height(220.dp)
            .padding(horizontal = 8.dp, vertical = 12.dp)
            .shadow(15.dp, shape = RoundedCornerShape(5.dp)), shape = RoundedCornerShape(15.dp)

    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text,
                    fontSize = 23.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontFamily =Constants.FontEnglish),
                )
                Text(
                    textAr,
                    fontSize = 23.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    style = TextStyle(fontFamily =Constants.FontArabic),
                )

            }

        }

    }
}

@Composable
fun HeaderDesign(title: String,titleAr: String, navController: NavController) {

    Box(modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp)) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))

        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(verticalArrangement = Arrangement.Center,modifier  =Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(title, fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.Black,
                            style = TextStyle(fontFamily =Constants.FontEnglish),)
                        Text(titleAr, fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.Black,
                            style = TextStyle(fontFamily =Constants.FontArabic),)

                    }

                }

            }


        }
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = Constants.LOGO),
                contentScale = ContentScale.FillBounds,
                contentDescription = "bg",
                modifier = Modifier
                    .width(OPTION_WIDTH)
                    .height(70.dp)
                    .pointerInput(Unit) {
                        detectDragGestures { change, _ ->
                            Log.d("TAG", "HeaderDesign: called")
                            if (change.position.y > 400) {
                                Log.d("TAG", "HeaderDesign: called1")
                                navController.navigate(Screen.SettingScreen.route)
                            }
                            change.consume()
                        }
                    }
            )
        }
    }

}
