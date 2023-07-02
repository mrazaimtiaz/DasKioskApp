package com.gicproject.salamkioskapp.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gicproject.salamkioskapp.Screen
import com.gicproject.salamkioskapp.common.Constants
import com.google.accompanist.flowlayout.FlowColumn
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SelectTestServiceScreen(
    navController: NavController,
    viewModel: MyViewModel,
) {

    val second = remember { mutableStateOf(120) }

    val state = viewModel.stateSelectTestService.value;

    LaunchedEffect(true) {

        Log.d(
            "TAG",
            "SelectTestServiceScreen:"
        )
        viewModel.onEvent(MyEvent.GetSelectTestServices)
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
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(20.dp)
                ) {
                   GoBack(navController)
                }
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom
            ) {
                HeartBeatTime(second = second)
            }
            HeaderDesign("Select Service","حدد الخدمة",navController)
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
                        .height(Constants.SERVICE_HEIGHT),
                    columns = GridCells.Fixed(2),
                ) {
                    items(state.options.size) { index ->
                        CustomButton(onClick = {
                            navController.navigate(Screen.SelectChildServiceScreen.route)
                        }, text = state.options[index].DepartmentNameEN ?:"",state.options[index].DepartmentNameAR ?:"")


                    }
                }
            }

        /*    Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CustomButton(onClick = {
                        navController.navigate(Screen.SelectChildServiceScreen.route)
                    }, text = "Laboratory","معمل")

                    CustomButton(onClick = {
                        navController.navigate(Screen.SelectChildServiceScreen.route)
                    }, text = "X-Rays","الأشعة السينية")
                }
                Spacer(modifier = Modifier.height(30.dp))

            }*/


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


