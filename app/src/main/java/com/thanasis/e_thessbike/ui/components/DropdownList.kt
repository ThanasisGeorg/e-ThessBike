package com.thanasis.e_thessbike.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.thanasis.e_thessbike.NotificationService

@Composable
fun DropdownList(itemList: List<String>, selectedIndex: Int, onItemClick: (Int) -> Unit, text: String) {
    var showDropdown by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            label = { Text(text = text) },
            modifier = Modifier.width(180.dp),
            value = itemList[selectedIndex],
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                val arrowDropDown = Icons.Filled.ArrowDropDown
                
                IconButton(
                    onClick = { showDropdown = !showDropdown }
                ) {
                    Icon(imageVector = arrowDropDown, contentDescription = "")
                }
            }
        )
        Spacer(modifier = Modifier.padding(5.dp, 0.dp))
        Column(
            modifier = Modifier.height(300.dp)
        ) {
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
                                    Text(text = item)
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OptionsDropdown(userLoggedIn: Array<String>, index: Int, navHostController: NavHostController, notificationService: NotificationService) {
    var showDropdown by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.Start
    ) {
        Column(
            modifier = Modifier.height(115.dp)
        ) {
            IconButton(
                enabled = true,
                modifier = Modifier.size(50.dp, 50.dp),
                content = { Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "") },
                onClick = { showDropdown = !showDropdown }
            )
            if (showDropdown){
                Box {
                    Popup(
                        properties = PopupProperties(
                            excludeFromSystemGesture = true,
                        ),
                        onDismissRequest = { showDropdown = false }
                    ) {
                        Column(
                            modifier = Modifier
                                .width(120.dp)
                                .heightIn(max = 400.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            RemoveButton(userLoggedIn, index, navHostController, notificationService)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorListDropdown(errorList: MutableList<String>, index: Int) {
    Box {
        Popup {
            Column {
                Box(
                    modifier = Modifier.padding(0.dp, 25.dp)
                ) {
                    Text(text = errorList[index])
                }
            }
        }
    }
}

@Preview
@Composable
fun DropdownListPreview() {
    OptionsDropdown(userLoggedIn = arrayOf(), index = 0, navHostController = rememberNavController(), notificationService = NotificationService(LocalContext.current))
}