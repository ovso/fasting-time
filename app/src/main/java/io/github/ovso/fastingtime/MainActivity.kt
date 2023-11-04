package io.github.ovso.fastingtime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var expand by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "단식 시작 시간")
        DropdownMenu(
            expanded = expand,
            onDismissRequest = { /*TODO*/ },
        ) {
            TextButton(onClick = { expand = !expand }) {
                Text(text = "15 시간 후")
            }
            TextButton(onClick = { expand = !expand }) {
                Text(text = "16 시간 후")
            }
            TextButton(onClick = { expand = !expand }) {
                Text(text = "17 시간 후")
            }

        }
        Text(text = "단식 종료 시간")
        TextButton(onClick = { expand = !expand }) {
            Text(text = "남은 시간")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewMainActivity() {
    MainScreen()
}