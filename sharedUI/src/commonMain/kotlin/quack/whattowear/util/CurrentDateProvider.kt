package quack.whattowear.util

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class CurrentDateProvider {
  fun getCurrentDateFormatted(): String {
    val now = Clock.System.now()
    val timeZone = TimeZone.currentSystemDefault()
    val localDateTime = now.toLocalDateTime(timeZone)
    return formatDate(localDateTime)
  }

  private fun formatDate(dateTime: LocalDateTime): String {
    return buildString {
      append(dateTime.dayOfMonth.toString().padStart(2, '0'))
      append('.')
      append(dateTime.monthNumber.toString().padStart(2, '0'))
      append('.')
      append(dateTime.year)
    }
  }
}