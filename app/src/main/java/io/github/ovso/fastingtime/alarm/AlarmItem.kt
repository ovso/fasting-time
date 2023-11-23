package io.github.ovso.fastingtime.alarm

import java.time.LocalDateTime

data class AlarmItem(
    val alarmTime : LocalDateTime,
    val message : String
)