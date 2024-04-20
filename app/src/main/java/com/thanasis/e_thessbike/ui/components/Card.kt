package com.thanasis.e_thessbike.ui.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thanasis.e_thessbike.R
import com.thanasis.e_thessbike.backend.Account
import com.thanasis.e_thessbike.backend.initInfo
import com.thanasis.e_thessbike.backend.signUp.SignUpViewModel

@Composable
fun ProfileCard() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .size(width = 383.dp, height = 250.dp)

    ) {
        Column {
            Row {
                IndicatorsSection()
                InfoSection()
            }
        }
    }
}

@Composable
fun IndicatorsSection() {
    Column {
        Text(
            text = stringResource(id = R.string.first_name_indicator),
            modifier = Modifier.padding(16.dp),style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
        )
        Text(
            text = stringResource(id = R.string.last_name_indicator),
            modifier = Modifier.padding(16.dp),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
        )
        Text(
            text = stringResource(id = R.string.email_indicator),
            modifier = Modifier.padding(16.dp),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun InfoSection() {
    val TAG = SignUpViewModel::class.simpleName

    Column {
        Spacer(modifier = Modifier.height(1.dp))

        FirstName()

        Spacer(modifier = Modifier.height(3.dp))

        LastName()

        Spacer(modifier = Modifier.height(1.dp))

        Email()
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun FirstName() {
    val account: MutableState<Account> = initInfo("users_info", 0, "name")
    val TAG: String? = SignUpViewModel::class.simpleName
    Log.d(TAG, "fName: ${account.value.firstName}")
    Text(
        text = account.value.firstName,
        modifier = Modifier.padding(16.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        ),
        textAlign = TextAlign.Center,
    )
}

@SuppressLint("CoroutineCreationDuringComposition", "UnrememberedMutableState")
@Composable
fun LastName() {
    val account = Account()
    val TAG: String? = SignUpViewModel::class.simpleName

    Log.d(TAG, "lName: ${account.lastName}")
    Text(
        text = account.lastName,
        modifier = Modifier.padding(16.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        ),
        textAlign = TextAlign.Center,
    )
}

@SuppressLint("CoroutineCreationDuringComposition", "UnrememberedMutableState")
@Composable
fun Email() {
    val account = Account()
    val TAG: String? = SignUpViewModel::class.simpleName

    Log.d(TAG, "email: ${account.email}")
    Text(
        text = account.email,
        modifier = Modifier.padding(16.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        ),
        textAlign = TextAlign.Center,
    )
}

@Preview
@Composable
fun ProfileCardPreview() {
    ProfileCard()
}
