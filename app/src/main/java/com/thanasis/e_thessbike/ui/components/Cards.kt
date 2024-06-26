package com.thanasis.e_thessbike.ui.components

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.QuerySnapshot
import com.thanasis.e_thessbike.NotificationService
import com.thanasis.e_thessbike.R
import com.thanasis.e_thessbike.backend.getData
import com.thanasis.e_thessbike.backend.initInfo

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BikeCard(indexesOfBikes: ArrayList<Int>, index: Int, task: QuerySnapshot, userLoggedIn: Array<String>, navHostController: NavHostController, notificationService: NotificationService) {
    Scaffold(
        modifier = Modifier.height(280.dp),
        floatingActionButton = {
            if (task.documents[indexesOfBikes[index]].getString("email").equals(userLoggedIn[1])) {
                Column {
                    OptionsDropdown(
                        userLoggedIn,
                        index,
                        navHostController,
                        notificationService
                    )
                    Spacer(modifier = Modifier.padding(0.dp, 75.dp))
                }
            }
        }
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = Modifier.size(width = 410.dp, height = 280.dp),
            onClick = {}
        ) {
            Row(
                modifier = Modifier.width(410.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row {
                        BikeInfoSection(indexesOfBikes, index, task)
                    }
                }
                /*if (task.documents[indexesOfBikes[index]].getString("email").equals(userLoggedIn[1])) {
                    OptionsDropdown(
                        userLoggedIn,
                        index,
                        navHostController,
                        notificationService
                    )
                }*/
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BikeCard_() {
    Scaffold(
        modifier = Modifier.height(190.dp),
        //floatingActionButton = { EditButton(navHostController = rememberNavController()) }
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = Modifier.size(width = 410.dp, height = 210.dp),
            onClick = {}
        ) {
            Row(
                modifier = Modifier.width(410.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column {
                    Text(
                        text = stringResource(id = R.string.brand_name_indicators),
                        modifier = Modifier.padding(16.dp),
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = stringResource(id = R.string.color_indicators),
                        modifier = Modifier.padding(16.dp),
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = stringResource(id = R.string.color_indicators),
                        modifier = Modifier.padding(16.dp),
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = stringResource(id = R.string.color_indicators),
                        modifier = Modifier.padding(16.dp),
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center,
                    )
                }
                IconButton(
                    enabled = true,
                    modifier = Modifier.size(50.dp, 50.dp),
                    content = { Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "") },
                    onClick = { /*TODO*/ }
                )
            }
        }
    }
}

@Composable
fun BikeInfoSection(indexesOfBikes: ArrayList<Int>, index: Int, task: QuerySnapshot) {
    Column(
        modifier = Modifier.height(280.dp)
    ) {
        Spacer(modifier = Modifier.height(1.dp))

        BrandName(indexesOfBikes, index, task)

        Spacer(modifier = Modifier.height(1.dp))

        Color(indexesOfBikes, index, task)

        Spacer(modifier = Modifier.height(1.dp))

        Location(indexesOfBikes, index, task)

        Spacer(modifier = Modifier.height(1.dp))

        EmailInfo(indexesOfBikes, index, task)
    }
}

@Composable
fun BrandName(indexesOfBikes: ArrayList<Int>, index: Int, task: QuerySnapshot) {
    task.documents[indexesOfBikes[index]].getString("brand_name")?.let {
        Row {
            Text(
                text = stringResource(id = R.string.brand_name_indicators),
                modifier = Modifier.padding(16.dp),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center,
            )
            Text(
                text = it,
                modifier = Modifier.padding(0.dp, 17.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun Color(indexesOfBikes: ArrayList<Int>, index: Int, task: QuerySnapshot) {
    task.documents[indexesOfBikes[index]].getString("color")?.let {
        Row {
            Text(
                text = stringResource(id = R.string.color_indicators),
                modifier = Modifier.padding(16.dp),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center,
            )
            Text(
                text = it,
                modifier = Modifier.padding(0.dp, 17.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun Location(indexesOfBikes: ArrayList<Int>, index: Int, task: QuerySnapshot) {
    task.documents[indexesOfBikes[index]].getString("location")?.let {
        Row {
            Text(
                text = stringResource(id = R.string.location_indicator),
                modifier = Modifier.padding(16.dp),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center,
            )
            Text(
                text = it,
                modifier = Modifier.padding(0.dp, 17.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun EmailInfo(indexesOfBikes: ArrayList<Int>, index: Int, task: QuerySnapshot) {
    task.documents[indexesOfBikes[index]].getString("email")?.let {
        Row {
            Text(
                text = stringResource(id = R.string.owner_mail),
                modifier = Modifier.padding(16.dp),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center,
            )
            Text(
                text = it,
                modifier = Modifier.padding(0.dp, 17.dp),
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileCard(userLoggedIn: Array<String>, navHostController: NavHostController, context: Context, notificationService: NotificationService) {
    Scaffold(
        modifier = Modifier.height(250.dp),
        floatingActionButton = {
            Row {
                EditButton(navHostController)
                Spacer(modifier = Modifier.padding(12.dp, 0.dp))
                DeleteButton(userLoggedIn, navHostController, context, notificationService)
            }
        }
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = Modifier
                .size(width = 383.dp, height = 250.dp)

        ) {
            Column {
                Row {
                    ProfileInfoSection(userLoggedIn)
                }
            }
        }
    }
}

@Composable
fun ProfileInfoSection(userLoggedIn: Array<String>) {
    Column {
        Spacer(modifier = Modifier.height(1.dp))

        FirstName(userLoggedIn)

        Spacer(modifier = Modifier.height(3.dp))

        LastName(userLoggedIn)

        Spacer(modifier = Modifier.height(1.dp))

        Email(userLoggedIn)
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun FirstName(tempArray: Array<String>) {
    if (tempArray[0].isNotEmpty()) {
        val data = initInfo("users_info")
        val userLoggedIn = getData(tempArray)

        Row {
            Text(
                text = stringResource(id = R.string.first_name_indicator),
                modifier = Modifier.padding(16.dp),
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center,
            )
            Text(
                text = data.documents[userLoggedIn[0].toInt()].getString("name").toString(),
                modifier = Modifier.padding(20.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition", "UnrememberedMutableState")
@Composable
fun LastName(tempArray: Array<String>) {
    if (tempArray[0].isNotEmpty()) {
        val data = initInfo("users_info")
        val userLoggedIn = getData(tempArray)

        Row {
            Text(
                text = stringResource(id = R.string.last_name_indicator),
                modifier = Modifier.padding(16.dp),
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center,
            )
            Text(
                text = data.documents[userLoggedIn[0].toInt()].getString("surname").toString(),
                modifier = Modifier.padding(20.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition", "UnrememberedMutableState")
@Composable
fun Email(tempArray: Array<String>) {
    if (tempArray[0].isNotEmpty()) {
        val data = initInfo("users_info")
        val userLoggedIn = getData(tempArray)

        Row {
            Text(
                text = stringResource(id = R.string.email_indicator),
                modifier = Modifier.padding(16.dp),
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center,
            )
            Text(
                text = data.documents[userLoggedIn[0].toInt()].getString("email").toString(),
                modifier = Modifier.padding(20.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Center,
            )
        }
    }
}
