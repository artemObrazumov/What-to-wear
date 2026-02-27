package quack.whattowear.feature.main_screen.impl.domain.models

import org.jetbrains.compose.resources.StringResource
import whattowear.sharedui.generated.resources.Res
import whattowear.sharedui.generated.resources.cloudy
import whattowear.sharedui.generated.resources.overcast
import whattowear.sharedui.generated.resources.rain
import whattowear.sharedui.generated.resources.snow
import whattowear.sharedui.generated.resources.sunny

enum class WeatherStatus(val label: StringResource) {
  SUNNY(Res.string.sunny),
  CLOUDY(Res.string.cloudy),
  OVERCAST(Res.string.overcast),
  RAIN(Res.string.rain),
  SNOW(Res.string.snow)
}

fun String.toWeatherStatus(): WeatherStatus {
  return when (this.uppercase()) {
    "SUNNY" -> WeatherStatus.SUNNY
    "CLOUDY" -> WeatherStatus.CLOUDY
    "OVERCAST" -> WeatherStatus.OVERCAST
    "RAIN" -> WeatherStatus.RAIN
    "SNOW" -> WeatherStatus.SNOW
    else -> WeatherStatus.SUNNY
  }
}