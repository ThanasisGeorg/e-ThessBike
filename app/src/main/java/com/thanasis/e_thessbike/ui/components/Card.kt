package com.thanasis.e_thessbike.ui.components

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
    val account = Account()

    Column {
        Spacer(modifier = Modifier.height(1.dp))

        account.FirstName()

        Spacer(modifier = Modifier.height(3.dp))

        account.LastName()

        Spacer(modifier = Modifier.height(1.dp))

        account.Email()
    }
}

@Preview
@Composable
fun ProfileCardPreview() {
    ProfileCard()
}
