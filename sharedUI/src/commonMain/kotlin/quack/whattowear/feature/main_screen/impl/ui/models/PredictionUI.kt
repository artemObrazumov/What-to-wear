package quack.whattowear.feature.main_screen.impl.ui.models

import quack.whattowear.feature.main_screen.impl.domain.models.Prediction

class PredictionUI(
  val timeLabel: String,
  val weatherStatus: WeatherStatusUI,
  val temperatureString: String,
)

fun Prediction.toUi(): PredictionUI {
  return PredictionUI(
    timeLabel = time,
    weatherStatus = weatherStatus.toWeatherStatusUI(),
    temperatureString = temperature.toString()
  )
}
