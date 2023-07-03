package com.gicproject.salamkioskapp.presentation

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gicproject.salamkioskapp.Screen
import com.gicproject.salamkioskapp.common.Constants
import com.google.accompanist.flowlayout.FlowColumn
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.delay


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SelectChildTestServiceScreen(
      parentId: String,
    navController: NavController,
    viewModel: MyViewModel,
) {

    val second = remember { mutableStateOf(120) }


    val state = viewModel.stateSelectChildTestService.value;

    LaunchedEffect(true) {

        Log.d(
            "TAG",
            "SelectTestServiceScreen: ${parentId}"
        )
        viewModel.onEvent(MyEvent.GetChildTestServices(parentId))
    }


    var showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        if (showDialog.value) {
            PaymentDialog(showDialog, navController)
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 100.dp)
            ) {

                val total = remember { mutableStateOf(0) }


                val configuration = LocalConfiguration.current

                val screenHeight = configuration.screenHeightDp


                HeaderDesign("Select Test","حدد اختبار",navController)
                
                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,) {
                    LazyVerticalGrid(
                        modifier = Modifier.height((screenHeight / 1.75).dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        state = rememberLazyGridState(),

                        contentPadding = PaddingValues(start =20.dp,end = 20.dp,),
                        columns = GridCells.Fixed(2),
                    ) {
                        items(state.services.size) { index ->



                            val isTest1 = remember { mutableStateOf(false) }
                            MyCheckBox(state.services[index].ServicesNameEN ?: "", "15", isTest1, total)

                            Spacer(modifier = Modifier.height(10.dp))
                        }

                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text("Total: ${total.value} KD ", fontSize = 35.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(15.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SubmitButton(onClick = {
                            showDialog.value = true
                        }, text = "Proceed to Pay")
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
}

@Composable
fun MyCheckBox(
    title: String,
    price: String,
    isCheck: MutableState<Boolean>,
    total: MutableState<Int>
) {
        Box(modifier = Modifier.padding(vertical = 5.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                    .height(60.dp)
                    .width(300.dp)
                    .clickable {
                        isCheck.value = !isCheck.value
                        if (isCheck.value) {
                            total.value = total.value + price.toInt()
                        } else {
                            total.value = total.value - price.toInt()
                        }
                    },
            ) {
                Row(modifier = Modifier.weight(2f)) {
                    Spacer(modifier = Modifier.width(5.dp))
                    Card(
                        modifier = Modifier.background(Color.White),
                        elevation = 0.dp,
                        shape = RoundedCornerShape(6.dp),
                        border = BorderStroke(1.5.dp, color = MaterialTheme.colors.secondary)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(35.dp)
                                .background(if (isCheck.value) MaterialTheme.colors.secondary else Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isCheck.value)
                                Icon(
                                    Icons.Default.Check,
                                    contentDescription = "",
                                    tint = Color.White,
                                    modifier = Modifier.size(35.dp)
                                )
                        }
                    }
                }
                Row(modifier = Modifier.weight(5f)) {
                    Text(
                        text = title,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 25.sp,
                        color = Color.Black
                    )
                }

                Row(modifier = Modifier.weight(4f)) {
                    Text(
                        text = "$price KD",
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))

        }


}


