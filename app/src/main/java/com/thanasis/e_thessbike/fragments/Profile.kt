package com.thanasis.e_thessbike.fragments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.thanasis.e_thessbike.components.NavDrawer
import com.thanasis.e_thessbike.ui.theme.EThessBikeTheme

class ProfileFragment : ComponentActivity(){
    private val navDrawer = NavDrawer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EThessBikeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navDrawer.MenuDrawer()
                    Info()
                }
            }
        }
    }

    @Composable
    @Preview
    fun Info() {
        Text("Hello World")
    }
}