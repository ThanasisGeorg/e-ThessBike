package com.thanasis.e_thessbike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.thanasis.e_thessbike.components.NavDrawer
import com.thanasis.e_thessbike.ui.theme.EThessBikeTheme

class MainActivity : ComponentActivity() {
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
                }
            }
        }
    }
}