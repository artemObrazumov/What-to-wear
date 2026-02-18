package quack.whattowear.feature.main_screen.impl.ui.models

import quack.whattowear.feature.main_screen.impl.domain.models.Forecast

class ForecastUI(
  val temperature: String,
  val feelsLikeTemperature: String,
  val wind: String,
  val windDirection: String,
  val humidity: String,
  val humidityMeasurement: String,
  val pressure: String,
  val pressureMeasurement: String,
)

fun Forecast.toUI(): ForecastUI = ForecastUI(
  temperature = temperature.toString(),
  feelsLikeTemperature = feelsLikeTemperature.toString(),
  wind = wind.value.toString(),
  windDirection = wind.label,
  humidity = humidity.value.toString(),
  humidityMeasurement = humidity.label,
  pressure = pressure.value.toString(),
  pressureMeasurement = pressure.label,
)