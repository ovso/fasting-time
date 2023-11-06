package io.github.ovso.fastingtime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextAlign
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

    var expanded by remember { mutableStateOf(false) }
    var label by remember { mutableStateOf("15 시간 후 알림") }

    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
    ) {
        ElevatedAssistChip(
            onClick = { expanded = true },
            label = { Text(label) },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Notifications, contentDescription = null)
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("15 시간 후 알림") },
                onClick = { label = "Edit" },
            )
            DropdownMenuItem(
                text = { Text("Settings") },
                onClick = { label = "Settings" },
            )
            Divider()
            DropdownMenuItem(
                text = { Text("Send Feedback") },
                onClick = { label = "Send Feedback" },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainActivity() {
    MainScreen()
}