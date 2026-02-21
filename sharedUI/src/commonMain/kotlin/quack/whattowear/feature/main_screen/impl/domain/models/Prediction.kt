package quack.whattowear.feature.main_screen.impl.domain.models

class Prediction(
  // TODO: add time field
  val weatherStatus: WeatherStatus,
  val temperature: Temperature,
)