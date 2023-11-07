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
import androidx.compose.material3.Divider
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

    var startExpanded by remember { mutableStateOf(false) }
    var endExpanded by remember { mutableStateOf(false) }
    var label by remember { mutableStateOf("15 시간 후 알림") }

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
                onClick = { startExpanded = true },
                label = { Text(label) },
            )
            DropdownMenu(
                expanded = startExpanded,
                onDismissRequest = { startExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("00:30") },
                    onClick = { label = "00 시" },
                )
                DropdownMenuItem(
                    text = { Text("00 시") },
                    onClick = { label = "00 시" },
                )
                Divider()
                DropdownMenuItem(
                    text = { Text("00 시") },
                    onClick = { label = "00 시" },
                )
            }
        }

        Text(text = "공복 그만", style = MaterialTheme.typography.headlineLarge)
        Box(
            modifier = Modifier
                .wrapContentSize(Alignment.TopStart)
        ) {
            ElevatedAssistChip(
                onClick = { endExpanded = true },
                label = { Text(label) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Notifications, contentDescription = null)
                }
            )
            DropdownMenu(
                expanded = endExpanded,
                onDismissRequest = { endExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("15 시간 후 알림") },
                    onClick = { label = "15 시간 후 알림" },
                )
                DropdownMenuItem(
                    text = { Text("16 시간 후 알림") },
                    onClick = { label = "16 시간 후 알림" },
                )
                Divider()
                DropdownMenuItem(
                    text = { Text("17 시간 후 알림") },
                    onClick = { label = "17 시간 후 알림" },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainActivity() {
    MainScreen()
}