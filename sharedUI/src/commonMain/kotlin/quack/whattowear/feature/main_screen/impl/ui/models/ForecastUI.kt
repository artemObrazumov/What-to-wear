package quack.whattowear.feature.main_screen.impl.ui.models

import org.jetbrains.compose.resources.StringResource
import quack.whattowear.feature.main_screen.impl.domain.models.Forecast

class ForecastUI(
  val temperature: String,
  val feelsLikeTemperature: String,
  val wind: String,
  val windDirection: String,
  val humidity: String,
  val pressure: String,
  val pressureMeasurement: StringResource,
  val weatherStatus: WeatherStatusUI,
  val predictions: List<PredictionUI>,
)

fun Forecast.toUI(): ForecastUI = ForecastUI(
  temperature = temperature.toString(),
  feelsLikeTemperature = feelsLikeTemperature.toString(),
  wind = wind.value.toString(),
  windDirection = wind.label,
  humidity = humidity.toString(),
  pressure = pressure.value.toString(),
  pressureMeasurement = pressure.label,
  weatherStatus = status.toWeatherStatusUI(),
  predictions = predictions.map { it.toUi() }
)