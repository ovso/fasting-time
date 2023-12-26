package io.github.ovso.fastingtime

import android.app.AlarmManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.getSystemService
import androidx.core.view.WindowCompat
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import io.github.ovso.fastingtime.alarm.AlarmItem
import io.github.ovso.fastingtime.alarm.AlarmScheduler
import io.github.ovso.fastingtime.alarm.AlarmSchedulerImpl
import io.github.ovso.fastingtime.ui.theme.FastingtimeTheme
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // https://medium.com/@nipunvirat0/how-to-schedule-alarm-in-android-using-alarm-manager-7a1c3b23f1bb
        val alarmScheduler: AlarmScheduler = AlarmSchedulerImpl(this)
        var alarmItem: AlarmItem? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            TedPermission.create()
                .setPermissions(android.Manifest.permission.POST_NOTIFICATIONS)
                .setPermissionListener(object : PermissionListener {
                    override fun onPermissionGranted() {
                        Toast.makeText(this@MainActivity, "onPermissionGranted", Toast.LENGTH_SHORT).show()
                    }

                    override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                        Toast.makeText(this@MainActivity, "onPermissionDenied", Toast.LENGTH_SHORT).show()
                    }
                })
                .check()
        }

        val alarmManager = getSystemService<AlarmManager>()!!
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager.canScheduleExactAlarms()) {

                alarmItem = AlarmItem(
                    alarmTime = LocalDateTime.now().plusSeconds(10),
                    message = "alarmItem message"
                )

                alarmScheduler.schedule(alarmItem)
            } else {
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                startActivity(intent)
            }
        }

        setContent {
            FastingtimeTheme {

                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column {
                        MainScreen()

                        Spacer(modifier = Modifier.height(50.dp))

                        var secondsText by remember {
                            mutableStateOf("")
                        }

                        var message by remember {
                            mutableStateOf("")
                        }

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = secondsText,
                            onValueChange = {
                                secondsText = it
                            },
                            label = {
                                Text(text = "Trigger alarm in seconds")
                            }
                        )
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = message,
                            onValueChange = {
                                message = it
                            },
                            label = {
                                Text(text = "Message")
                            }
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Button(onClick = {
                                alarmItem = AlarmItem(
                                    alarmTime = LocalDateTime.now().plusSeconds(secondsText.toLong()),
                                    message = message
                                )
                                alarmItem?.let(alarmScheduler::schedule)
                                secondsText = ""
                                message = ""
                            }) {
                                Text(text = "Schedule")
                            }
                            Button(onClick = {
                                alarmItem?.let(alarmScheduler::cancel)
                                secondsText = ""
                                message = ""
                            }) {
                                Text(text = "Cancel")

                            }
                        }

                        Button(onClick = {
                            startActivity(Intent(this@MainActivity, DatePickerActivity::class.java))
                        }) {
                            Text(text = "DatePicker 보기")
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMainActivity() {

}