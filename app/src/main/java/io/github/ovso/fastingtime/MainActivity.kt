package io.github.ovso.fastingtime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.ovso.fastingtime.ui.theme.FastingtimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FastingtimeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {

    var startTimeExpanded by remember { mutableStateOf(false) }
    var notifictionDropDownExpanded by remember { mutableStateOf(false) }
    var notificationLabel by remember { mutableStateOf("") }
    var startTimeLabel by remember { mutableStateOf("00:00") }
    val startTimes = listOf(
        "00:00",
        "01:00",
        "02:00",
        "03:00",
        "04:00",
        "05:00",
        "06:00",
        "07:00",
        "08:00",
        "09:00",
        "10:00",
        "11:00",
        "12:00",
        "13:00",
        "14:00",
        "15:00",
        "16:00",
        "17:00",
        "18:00",
        "19:00",
        "20:00",
        "21:00",
        "22:00",
        "23:00",
        "24:00",
    )
    val notificationTimes = listOf(
        "15시간 후 알림",
        "16시간 후 알림",
        "17시간 후 알림",
        "18시간 후 알림"
    )


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "공복 시작", style = MaterialTheme.typography.headlineLarge)
        Box(
            modifier = Modifier
                .wrapContentSize(Alignment.TopStart)
        ) {
            ElevatedAssistChip(
                onClick = { startTimeExpanded = true },
                label = { Text(startTimeLabel) },
            )
            DropdownMenu(
                expanded = startTimeExpanded,
                onDismissRequest = { startTimeExpanded = false }
            ) {
                startTimes.forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            startTimeLabel = it
                            startTimeExpanded = false
                        }
                    ) }
            }
        }

        Text(text = "공복 그만", style = MaterialTheme.typography.headlineLarge)
        Box(
            modifier = Modifier
                .wrapContentSize(Alignment.TopStart)
        ) {
            ElevatedAssistChip(
                onClick = { notifictionDropDownExpanded = true },
                label = { Text(notificationLabel) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Notifications, contentDescription = null)
                }
            )
            DropdownMenu(
                expanded = notifictionDropDownExpanded,
                onDismissRequest = { notifictionDropDownExpanded = false }
            ) {
                notificationTimes.forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            notificationLabel = it
                            notifictionDropDownExpanded = false
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainActivity() {
    MainScreen()
}