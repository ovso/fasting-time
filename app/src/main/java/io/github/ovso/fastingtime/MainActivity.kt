package io.github.ovso.fastingtime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import io.github.ovso.fastingtime.alarm.AlarmItem
import io.github.ovso.fastingtime.alarm.AlarmScheduler
import io.github.ovso.fastingtime.alarm.AlarmSchedulerImpl
import io.github.ovso.fastingtime.ui.theme.FastingtimeTheme
import java.time.LocalDateTime
import java.time.Month

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // https://medium.com/@nipunvirat0/how-to-schedule-alarm-in-android-using-alarm-manager-7a1c3b23f1bb
        val alarmScheduler: AlarmScheduler = AlarmSchedulerImpl(this)
        val alarmItem = AlarmItem(
            alarmTime = LocalDateTime.of(2023, Month.DECEMBER, 15, 21, 35),
            message = "message"
        )
        alarmScheduler.schedule(alarmItem)
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


@Preview(showBackground = true)
@Composable
fun PreviewMainActivity() {

}