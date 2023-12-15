package io.github.ovso.fastingtime.alarm

import android.content.Context

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.content.getSystemService
import java.time.ZoneId

class AlarmSchedulerImpl(
    private val context: Context,
) : AlarmScheduler {

    private val alarmManager = context.getSystemService<AlarmManager>()!!

    @Throws(SecurityException::class)
    override fun schedule(alarmItem: AlarmItem) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("EXTRA_MESSAGE", alarmItem.message)
        }
        val alarmTime = alarmItem.alarmTime.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000L
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            alarmTime,
            PendingIntent.getBroadcast(
                context,
                alarmItem.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    override fun cancel(alarmItem: AlarmItem) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                alarmItem.hashCode(),
                Intent(context, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}

interface AlarmScheduler {
    fun schedule(alarmItem: AlarmItem)
    fun cancel(alarmItem: AlarmItem)
}