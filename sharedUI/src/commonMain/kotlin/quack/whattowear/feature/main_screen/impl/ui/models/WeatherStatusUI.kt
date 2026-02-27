package quack.whattowear.feature.main_screen.impl.ui.models

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import quack.whattowear.feature.main_screen.impl.domain.models.WeatherStatus
import whattowear.sharedui.generated.resources.Res
import whattowear.sharedui.generated.resources.cloudy
import whattowear.sharedui.generated.resources.rain
import whattowear.sharedui.generated.resources.snow
import whattowear.sharedui.generated.resources.sunny

data class WeatherStatusUIData(
  val icon: DrawableResource,
  val label: StringResource,
)

fun WeatherStatus.toWeatherStatusUIData(): WeatherStatusUIData {
  return when (this) {
    WeatherStatus.SUNNY -> WeatherStatusUIData(
      icon = Res.drawable.sunny,
      label = this.label
    )

    WeatherStatus.CLOUDY -> WeatherStatusUIData(
      icon = Res.drawable.cloudy,
      label = this.label
    )

    WeatherStatus.OVERCAST -> WeatherStatusUIData(
      icon = Res.drawable.cloudy,
      label = this.label
    )

    WeatherStatus.RAIN -> WeatherStatusUIData(
      icon = Res.drawable.rain,
      label = this.label
    )

    WeatherStatus.SNOW -> WeatherStatusUIData(
      icon = Res.drawable.snow,
      label = this.label
    )
  }
}