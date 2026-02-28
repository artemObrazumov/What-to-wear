package quack.whattowear.network.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import quack.whattowear.feature.geolocation.domain.model.GeoPoint
import quack.whattowear.feature.main_screen.impl.ui.models.Gender
import quack.whattowear.network.models.AiResponse
import quack.whattowear.network.models.ForecastResponse
import quack.whattowear.network.models.WeatherResponse

class KtorWeatherApi(private val client: HttpClient) : WeatherApi {
  override suspend fun getWeather(location: GeoPoint): WeatherResponse {
    val result = client.get("$API_URL/weather") {
      parameter("latitude", location.latitude)
      parameter("longitude", location.longitude)
    }
    return result.body()
  }

  override suspend fun getForecast(location: GeoPoint): ForecastResponse {
    val result = client.get("$API_URL/weather/forecast") {
      parameter("latitude", location.latitude)
      parameter("longitude", location.longitude)
    }
    return result.body()
  }

  override suspend fun getAiClothes(location: GeoPoint, gender: Gender): AiResponse {
    val result = client.get("$API_URL/ai") {
      parameter("latitude", location.latitude)
      parameter("longitude", location.longitude)
      val genderChar = when (gender) {
        Gender.Male -> 'm'
        Gender.Female -> 'f'
      }
      parameter("gender", genderChar)
    }
    return result.body()
  }

  private companion object {
    const val API_URL = "http://185.135.82.149:8000/api/v1"
  }
}
