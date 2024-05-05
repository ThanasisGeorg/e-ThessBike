package com.thanasis.e_thessbike.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.thanasis.e_thessbike.R

@Composable
fun DropdownList(itemList: List<String>, selectedIndex: Int, onItemClick: (Int) -> Unit) {
    var showDropdown by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Row {
        Text(
            text = stringResource(id = R.string.location),
            modifier = Modifier.padding(16.dp, 0.dp),
            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
        )
        Column(modifier = Modifier.height(300.dp)) {
            Box(
                modifier = Modifier
                    .clickable { showDropdown = !showDropdown }
                    .width(120.dp),
                contentAlignment = Alignment.Center,
            ){
                Text(
                    text = itemList[selectedIndex],
                    modifier = Modifier.padding(3.dp),
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.padding(0.dp, 5.dp))
            if (showDropdown) {
                Box {
                    Popup(
                        properties = PopupProperties(
                            excludeFromSystemGesture = true,
                        ),
                        onDismissRequest = { showDropdown = false }
                    ) {
                        Column(
                            modifier = Modifier
                                .width(95.dp)
                                .heightIn(max = 400.dp)
                                .verticalScroll(state = scrollState),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            itemList.onEachIndexed { index, item ->
                                Box(
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .fillMaxWidth()
                                        .clickable {
                                            onItemClick(index)
                                            showDropdown = !showDropdown
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = item,)
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DropdownListPreview() {
    DropdownList(itemList = listOf<String>("Sikies", "Neapoli", "Stavroupoli", "Evosmos", "Polichni", "Thessaloiniki", "Kalamaria", "Meteora"), selectedIndex = 0, onItemClick = {})
}