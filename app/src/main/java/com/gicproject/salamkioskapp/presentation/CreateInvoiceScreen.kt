package com.gicproject.salamkioskapp.presentation

import android.os.Build
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
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
import androidx.compose.ui.viewinterop.AndroidView
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
fun CreateInvoiceScreen(
    selectDepartment: SelectDepartment?,
    patient: Patient?,
    service: SelectService?,
    navController: NavController,
    viewModel: MyViewModel,
    ) {

    val stateConsultVisit = viewModel.stateConsultVisitInfo.value
    val state = viewModel.stateCreateInvoice.value
    val context = LocalContext.current
    LaunchedEffect(true) {
        if(stateConsultVisit.consultVisit != null){
            Log.d("TAG", "CreateConsultVisit: called ${selectDepartment?.DepartmentPKID} ")
            viewModel.hideBar(context)
            viewModel.onEvent(MyEvent.CreateInvoice(selectDepartment,patient,service,stateConsultVisit.consultVisit))
        }


    }

    val second = remember { mutableStateOf(180) }



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
                    if(!state.isLoading){
                        GreenButton(onClick = {

                            navController.popBackStack(Screen.SelectOptionScreen.route, false)
                        }, text = "Done", textAr = "")


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


                if(!state.isLoading && state.invoice != null){


                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        state.invoice.reportLink?.let {
                            Log.d("TAG", "CreateInvoiceScreen: ${state.invoice.reportLink}")
                            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                                PdfWebView(it)
                            }
                            }

                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    GreenButton(onClick = {

                        navController.popBackStack(Screen.SelectOptionScreen.route, false)
                    }, text = "Done", textAr = "")

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
fun PdfWebView(url: String) {
    AndroidView(
        factory = { context ->
        WebView(context).apply {
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                    view.loadUrl(request.url.toString())
                    return true
                }
            }
        }
    }) { webView ->
        // Load the PDF URL
        Log.d("TAG", "PdfWebView: url called $url")
        webView.loadUrl(url)
    }
}




