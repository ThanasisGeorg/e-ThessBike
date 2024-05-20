package com.thanasis.e_thessbike.ui.components

import android.content.ContentValues
import android.util.Log
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.backend.roomAPI.AppDatabase
import com.thanasis.e_thessbike.backend.roomAPI.Settings
import com.thanasis.e_thessbike.backend.signUp.SignUpViewModel
import com.thanasis.e_thessbike.ui.theme.MyTheme
import com.thanasis.e_thessbike.ui.theme.ThemeManager

@Composable
fun ThemeSwitch(
    navHostController: NavHostController,
    size: Dp = 150.dp,
    iconSize: Dp = size / 3,
    padding: Dp = 10.dp,
    borderWidth: Dp = 1.dp,
    parentShape: Shape = CircleShape,
    toggleShape: Shape = CircleShape,
    animationSpec: AnimationSpec<Dp> = tween(durationMillis = 300),
    userLoggedIn: Array<String>,
    roomDb: AppDatabase
) {
    val offset by animateDpAsState(
        targetValue = if (ThemeManager.getCurrentTheme() == MyTheme.Dark) 0.dp else size,
        animationSpec = animationSpec, label = ""
    )
    val settingsDao = roomDb.settingsDao()

    Box(modifier = Modifier
        .width(size * 2)
        .height(size)
        .clip(shape = parentShape)
        .clickable {
            if (ThemeManager.getCurrentTheme() != MyTheme.Dark) {
                ThemeManager.setTheme(MyTheme.Dark)
                settingsDao.updateSettings(Settings(userId = userLoggedIn[1], theme = "dark"))
                Log.d(ContentValues.TAG, "Room API: ${settingsDao.getSettings()}")
                navHostController.navigate(EThessBikeApp.Settings.name)
            } else {
                ThemeManager.setTheme(MyTheme.Light)
                settingsDao.updateSettings(Settings(userId = userLoggedIn[1], theme = "light"))
                Log.d(ContentValues.TAG, "Room API: ${settingsDao.getSettings()}")
                navHostController.navigate(EThessBikeApp.Settings.name)
            }
        }
        .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .offset(x = offset)
                .padding(all = padding)
                .clip(shape = toggleShape)
                .background(MaterialTheme.colorScheme.primary)
        ) {}
        Row(
            modifier = Modifier
                .border(
                    border = BorderStroke(
                        width = borderWidth,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    shape = parentShape
                )
        ) {
            Box(
                modifier = Modifier
                    .size(size),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(iconSize),
                    imageVector = Icons.Default.Nightlight,
                    contentDescription = "Theme Icon",
                    tint = if (ThemeManager.getCurrentTheme() == MyTheme.Dark) MaterialTheme.colorScheme.secondaryContainer
                    else MaterialTheme.colorScheme.primary
                )
            }
            Box(
                modifier = Modifier.size(size),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    imageVector = Icons.Default.LightMode,
                    contentDescription = "Theme Icon",
                    tint = if (ThemeManager.getCurrentTheme() == MyTheme.Dark) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.secondaryContainer
                )
            }
        }
    }
}

@Composable
fun ThemeSwitcher(roomDb: AppDatabase, darkTheme: Boolean ,onThemeUpdated: () -> Unit, navHostController: NavHostController) {
    var checked = darkTheme
    val settingsDao = roomDb.settingsDao()
    var settings = Settings(settingsDao.getSettings().userId, settingsDao.getSettings().theme)
    val TAG: String? = SignUpViewModel::class.simpleName

    //if (settingsDao.getTheme() == "light") checked = true

    Switch(
        checked = !darkTheme,
        onCheckedChange = {
            checked = it
            onThemeUpdated()
            navHostController.navigate(EThessBikeApp.Settings.name)
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colorScheme.primary,
            checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
            uncheckedThumbColor = MaterialTheme.colorScheme.primary,
            uncheckedTrackColor = MaterialTheme.colorScheme.primaryContainer,
            uncheckedBorderColor = MaterialTheme.colorScheme.primaryContainer
        ),
        thumbContent =
            if (!checked) {
                {
                    Icon(
                        imageVector = Icons.Filled.WbSunny,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                        tint = MaterialTheme.colorScheme.primaryContainer
                    )

                    settings = settings.copy(theme = "light")

                    settingsDao.updateSettings(Settings(settingsDao.getSettings().userId, "light"))
                    Log.d(TAG, settingsDao.getSettings().toString())
                }
            } else {
                {
                    Icon(
                        imageVector = Icons.Filled.Nightlight,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                        tint = MaterialTheme.colorScheme.primaryContainer
                    )

                    settings = settings.copy(theme = "dark")

                    settingsDao.updateSettings(Settings(settingsDao.getSettings().userId, "dark"))
                    Log.d(TAG, settingsDao.getSettings().toString())
                }
            }
    )
}

@Preview
@Composable
fun ThemeSwitchPreview() {
    //ThemeSwitch()
}