package quack.whattowear.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
  @SerialName("forecast")
  val forecast: ForecastDto,
  @SerialName("address")
  val address: AddressDto
)

@Serializable
data class ForecastDto(
  @SerialName("temperature")
  val temperature: Double,
  @SerialName("temperatureFeelsLike")
  val temperatureFeelsLike: Double,
  @SerialName("pressure")
  val pressure: Int,
  @SerialName("humidity")
  val humidity: Int,
  @SerialName("wind")
  val wind: WindDto
)

@Serializable
data class WindDto(
  @SerialName("speed")
  val speed: Double,
  @SerialName("dir")
  val direction: String
)

@Serializable
data class AddressDto(
  @SerialName("localizedTitle")
  val localizedTitle: String
)
