package io.github.ovso.fastingtime

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun MainScreen() {
    val context = LocalContext.current
    var ctaBtnText by remember { mutableStateOf("시작") }
    var status by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            Spacer(modifier = Modifier.height(50.dp))
        },
        bottomBar = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .imePadding()
                    .navigationBarsPadding(),
                shape = RectangleShape,
                onClick = {
                    status = !status
                    ctaBtnText = if (status) "멈춤" else "시작"
                }
            ) {
                Text(text = ctaBtnText, style = MaterialTheme.typography.headlineLarge)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "공복 시작 시간", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(20.dp))
            TimeInput(
                modifier = Modifier,
                state = TimePickerState(19, 0, is24Hour = true)
            )
            Divider(modifier = Modifier.padding(vertical = 40.dp))
            TimeInput(
                modifier = Modifier,
                state = TimePickerState(15, 0, is24Hour = true)
            )
            Text(text = "이후 공복 멈춤", style = MaterialTheme.typography.headlineLarge)
        }
    }

    BackHandler {
        (context as? MainActivity)?.finish()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen()
}