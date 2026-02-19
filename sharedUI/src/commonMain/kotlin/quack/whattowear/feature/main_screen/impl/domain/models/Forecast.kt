package quack.whattowear.feature.main_screen.impl.domain.models

class Forecast(
  val temperature: Temperature,
  val feelsLikeTemperature: Temperature,
  val wind: StringLabeledParameter,
  val humidity: PercentageParameter,
  val pressure: LabeledParameter,
)