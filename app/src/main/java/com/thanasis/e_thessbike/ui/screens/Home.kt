package com.thanasis.e_thessbike.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PedalBike
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.thanasis.e_thessbike.R
import com.thanasis.e_thessbike.ui.components.HeadingText
import com.thanasis.e_thessbike.ui.components.NormalText
import com.thanasis.e_thessbike.ui.components.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeInit(navController: NavHostController, value: String){
    Scaffold(
        topBar = {
            TopBar(navController, title = value)
        },
        /*floatingActionButton = {
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                ChangeOrientationButton(context)
            }
        },*/
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(18.dp, 77.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            HeadingText("Welcome to e-ThessBike")
            Spacer(modifier = Modifier.height(30.dp))
            NormalText(stringResource(id = R.string.home_text_1), TextAlign.Center, 24)
            Spacer(modifier = Modifier.height(30.dp))
            NormalText(stringResource(id = R.string.home_text_2), TextAlign.Center, 24)
            Spacer(modifier = Modifier.height(30.dp))
            NormalText(stringResource(id = R.string.home_text_3), TextAlign.Center, 24)
            Spacer(modifier = Modifier.height(10.dp))
            Icon(
                imageVector = Icons.Filled.PedalBike,
                contentDescription = "",
                modifier = Modifier.size(300.dp)
            )
        }
    }
}

@Preview
@Composable
fun HomeInitPreview() {
    HomeInit(navController = rememberNavController(), value = "Home")
}