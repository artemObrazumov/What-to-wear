package quack.whattowear.feature.main_screen.impl.domain.models

class Forecast(
  val temperature: Temperature,
  val feelsLikeTemperature: Temperature,
  val wind: LabeledParameter,
  val humidity: LabeledParameter,
  val pressure: LabeledParameter,
)