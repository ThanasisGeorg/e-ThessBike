package com.thanasis.e_thessbike.ui.components

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.QuerySnapshot
import com.thanasis.e_thessbike.R
import com.thanasis.e_thessbike.backend.initInfo

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BikeCard(indexesOfBikes: ArrayList<Int>, index: Int, task: QuerySnapshot) {
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.height(210.dp),
        //floatingActionButton = { FavouriteButton() }
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = Modifier.size(width = 410.dp, height = 210.dp),
            onClick = {
                Toast.makeText(context, "Card clicked", Toast.LENGTH_LONG).show()
            }
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BikeCard_() {
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.height(190.dp),
        //floatingActionButton = { FavouriteButton() }
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = Modifier.size(width = 410.dp, height = 210.dp),
            onClick = {
                Toast.makeText(context, "Card clicked", Toast.LENGTH_LONG).show()
            }
        ) {
            Row(
                modifier = Modifier.width(410.dp),
                horizontalArrangement = Arrangement.SpaceBetween
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

@Preview
@Composable
fun BikeCardPreview() {
    BikeCard_()
}

@Composable
fun BikeInfoSection(indexesOfBikes: ArrayList<Int>, index: Int, task: QuerySnapshot) {
    Column {
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
                modifier = Modifier.padding(10.dp, 17.dp),
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
                modifier = Modifier.padding(10.dp, 17.dp),
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
                text = stringResource(id = R.string.location),
                modifier = Modifier.padding(16.dp),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center,
            )
            Text(
                text = it,
                modifier = Modifier.padding(10.dp, 17.dp),
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
                modifier = Modifier.padding(10.dp, 17.dp),
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
fun ProfileCard(userLoggedIn: Array<String>) {
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
fun FirstName(userLoggedIn: Array<String>) {
    if (userLoggedIn[0].isNotEmpty()) {
        val data = initInfo("users_info", "name")

        if (data != null) {
            Row {
                Text(
                    text = stringResource(id = R.string.first_name_indicator),
                    modifier = Modifier.padding(16.dp),style = TextStyle(
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
}

@SuppressLint("CoroutineCreationDuringComposition", "UnrememberedMutableState")
@Composable
fun LastName(userLoggedIn: Array<String>) {
    if (userLoggedIn[0].isNotEmpty()) {
        val data = initInfo("users_info", "surname")

        if (data != null) {
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
}

@SuppressLint("CoroutineCreationDuringComposition", "UnrememberedMutableState")
@Composable
fun Email(userLoggedIn: Array<String>) {
    if (userLoggedIn[0].isNotEmpty()) {
        val data = initInfo("users_info", "email")

        if (data != null) {
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
}
