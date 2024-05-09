package com.thanasis.e_thessbike.ui.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.thanasis.e_thessbike.NotificationService
import com.thanasis.e_thessbike.backend.getDocuments
import com.thanasis.e_thessbike.backend.getIndexesOfSpecificBikes
import com.thanasis.e_thessbike.ui.components.BikeCard
import com.thanasis.e_thessbike.ui.components.TextField
import com.thanasis.e_thessbike.ui.components.TopBar
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
@Composable
fun SearchInit(navHostController: NavHostController, value: String, userLoggedIn: Array<String>, notificationService: NotificationService, configuration: Configuration) {
    val scrollState = rememberScrollState()
    val indexesOfBikes = remember { mutableStateOf(ArrayList<Int>()) }
    val task = getDocuments("bikes").getCompleted()

    Scaffold(
        topBar = {
            TopBar(navHostController, title = value)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp, 77.dp)
        ) {
            Scaffold(
                modifier = Modifier.height(150.dp)
            ) {
                Column {
                    Text(
                        text = "Search by brand name",
                        modifier = Modifier.padding(0.dp, 16.dp),
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center
                    )
                    TextField(
                        labelValue = "Search",
                        onTextSelected = {
                            indexesOfBikes.value = getIndexesOfSpecificBikes(it)
                        }
                    )
                }
            }
            HorizontalDivider()
            Scaffold {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(0.dp, 30.dp)
                        .verticalScroll(state = scrollState)
                ) {
                    for (i in indexesOfBikes.value.indices) {
                        BikeCard(indexesOfBikes.value, i, task, userLoggedIn, navHostController, notificationService, configuration)
                        Spacer(modifier = Modifier.padding(0.dp, 5.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SearchInitPreview() {
    SearchInit(navHostController = rememberNavController(), value = "Search", userLoggedIn = arrayOf(), notificationService = NotificationService(LocalContext.current), configuration = Configuration())
}