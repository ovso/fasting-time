package io.github.ovso.fastingtime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.ovso.fastingtime.ui.picker.DatePickerDialogSample
import io.github.ovso.fastingtime.ui.theme.FastingtimeTheme

class DatePickerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FastingtimeTheme {
                DatePickerDialogSample()
            }
        }
    }
}

@Preview
@Composable
fun DatePickerActivityPreview() {
    FastingtimeTheme {
        DatePickerDialogSample()
    }
}