package quack.whattowear.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponse(
  @SerialName("predictions")
  val predictions: List<ForecastItemDto>
)

@Serializable
data class ForecastItemDto(
  @SerialName("status")
  val status: String,
  @SerialName("temperature")
  val temperature: Float,
  @SerialName("time")
  val time: String
)
