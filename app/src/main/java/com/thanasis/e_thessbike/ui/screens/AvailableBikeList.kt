package com.thanasis.e_thessbike.ui.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.thanasis.e_thessbike.NotificationService
import com.thanasis.e_thessbike.backend.getDocuments
import com.thanasis.e_thessbike.backend.getIndexesOfAvailableBikes
import com.thanasis.e_thessbike.ui.components.BikeCard
import com.thanasis.e_thessbike.ui.components.TopBar
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AvailableBikeListInit(navController: NavHostController, value: String, userLoggedIn: Array<String>, notificationService: NotificationService, configuration: Configuration){
    val scrollState = rememberScrollState()
    val indexesOfBikes = getIndexesOfAvailableBikes()
    val task = getDocuments("bikes").getCompleted()

    Scaffold(
        topBar = {
            TopBar(navController, title = value)
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(18.dp, 87.dp)
                .verticalScroll(state = scrollState)
        ) {
            for (i in indexesOfBikes.indices) {
                BikeCard(indexesOfBikes, i, task, userLoggedIn, navController, notificationService, configuration)
                Spacer(modifier = Modifier.padding(0.dp, 5.dp))
            }
        }
    }
}