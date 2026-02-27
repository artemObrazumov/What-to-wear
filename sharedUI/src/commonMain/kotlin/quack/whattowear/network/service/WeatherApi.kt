package quack.whattowear.network.service

import quack.whattowear.feature.geolocation.domain.model.GeoPoint
import quack.whattowear.network.models.AiResponse
import quack.whattowear.network.models.ForecastResponse
import quack.whattowear.network.models.WeatherResponse

interface WeatherApi {
  suspend fun getWeather(location: GeoPoint): WeatherResponse
  suspend fun getForecast(location: GeoPoint): ForecastResponse
  suspend fun getAiClothes(location: GeoPoint, gender: String): AiResponse
}
