package quack.whattowear.feature.main_screen.impl.ui.models

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import quack.whattowear.feature.main_screen.impl.domain.models.WeatherStatus
import whattowear.sharedui.generated.resources.Res
import whattowear.sharedui.generated.resources.sunny

class WeatherStatusUI(
  val label: StringResource,
  val icon: DrawableResource,
) {
  companion object {
    val Sunny = WeatherStatusUI(
      label = Res.string.sunny,
      icon = Res.drawable.sunny,
    )
  }
}

fun WeatherStatus.toWeatherStatusUI(): WeatherStatusUI {
  return when (this) {
    WeatherStatus.Sunny -> WeatherStatusUI.Sunny
  }
}