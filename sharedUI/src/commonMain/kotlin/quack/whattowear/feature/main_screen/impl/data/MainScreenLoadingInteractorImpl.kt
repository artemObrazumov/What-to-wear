package quack.whattowear.feature.main_screen.impl.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import quack.whattowear.feature.geolocation.domain.model.GeoPoint
import quack.whattowear.feature.main_screen.impl.domain.interactor.MainScreenLoadingInteractor
import quack.whattowear.feature.main_screen.impl.domain.models.AddressAndForecast
import quack.whattowear.feature.main_screen.impl.domain.models.ClothesAdvice
import quack.whattowear.feature.main_screen.impl.ui.models.Gender
import quack.whattowear.network.mappers.toDomain
import quack.whattowear.network.service.WeatherApi

class MainScreenLoadingInteractorImpl(
  private val api: WeatherApi,
  private val scope: CoroutineScope
) : MainScreenLoadingInteractor {
  private val _addressAndForecastFlow: MutableStateFlow<AddressAndForecast?> = MutableStateFlow(null)
  override val addressAndForecastFlow: StateFlow<AddressAndForecast?> = _addressAndForecastFlow

  private val _clothesAdviceFlow: MutableStateFlow<ClothesAdvice?> = MutableStateFlow(null)
  override val clothesAdviceFlow: StateFlow<ClothesAdvice?> = _clothesAdviceFlow

  override fun loadAddressAndForecast(location: GeoPoint) {
    scope.launch {
      retry(3) {
        val weather = async { api.getWeather(location) }
        val forecast = async { api.getForecast(location) }
        _addressAndForecastFlow.value = weather.await().toDomain(forecast.await())
      }
    }
  }

  override fun loadClothesAdvice(location: GeoPoint, gender: Gender) {
    scope.launch {
      _clothesAdviceFlow.value = null
      retry(3) {
        val clothes = api.getAiClothes(location, gender)
        _clothesAdviceFlow.value = clothes.toDomain()
      }
    }
  }

  private suspend fun retry(
    times: Int,
    delay: Long = 500L,
    block: suspend () -> Unit
  ) {
    repeat(times - 1) { attempt ->
      try {
        block()
        return
      } catch (_: Exception) {
        delay(delay * (attempt + 1))
      }
    }

    try {
      block()
    } catch (_: Exception) {
    }
  }
}