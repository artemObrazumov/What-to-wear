package quack.whattowear.feature.main_screen.impl.ui.models

import quack.whattowear.feature.main_screen.impl.domain.models.Prediction

data class PredictionUI(
  val time: String,
  val weatherStatus: WeatherStatusUIData,
  val temperature: String,
)

fun Prediction.toPredictionUI(): PredictionUI {
  return PredictionUI(
    time = time,
    weatherStatus = weatherStatus.toWeatherStatusUIData(),
    temperature = temperature.toString(),
  )
}
