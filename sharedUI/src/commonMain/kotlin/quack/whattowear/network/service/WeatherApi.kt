package quack.whattowear.network.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import quack.whattowear.feature.geolocation.domain.model.GeoPoint
import quack.whattowear.network.models.WeatherResponse

interface WeatherApi {
  suspend fun getWeather(location: GeoPoint): WeatherResponse
}

class KtorWeatherApi(private val client: HttpClient) : WeatherApi {
  override suspend fun getWeather(location: GeoPoint): WeatherResponse {
    val result = client.get("$API_URL/weather") {
      parameter("latitude", location.latitude)
      parameter("longitude", location.longitude)
    }
    return result.body()
  }

  private companion object {
    const val API_URL = "http://185.135.82.149:8000/api/v1"
  }
}
