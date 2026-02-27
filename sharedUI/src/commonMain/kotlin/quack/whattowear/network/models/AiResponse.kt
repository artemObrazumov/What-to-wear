package quack.whattowear.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AiResponse(
  @SerialName("headwear")
  val headwear: String,
  @SerialName("outerwear")
  val outerwear: String,
  @SerialName("footwear")
  val footwear: String,
  @SerialName("upperBodyClothing")
  val upperBodyClothing: String,
  @SerialName("lowerBodyClothing")
  val lowerBodyClothing: String,
  @SerialName("accessories")
  val accessories: String
)
