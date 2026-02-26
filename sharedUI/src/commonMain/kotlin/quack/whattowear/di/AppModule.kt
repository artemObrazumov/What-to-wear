package quack.whattowear.di

import dev.jordond.compass.geolocation.MobileGeolocator
import dev.jordond.compass.permissions.LocationPermissionController
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import quack.whattowear.feature.main_screen.impl.data.GeolocationInteractorImpl
import quack.whattowear.feature.main_screen.impl.data.MainScreenLoadingInteractorImpl
import quack.whattowear.feature.main_screen.impl.domain.interactor.GeolocationInteractor
import quack.whattowear.feature.main_screen.impl.domain.interactor.MainScreenLoadingInteractor
import quack.whattowear.feature.main_screen.impl.ui.MainScreenViewModel
import quack.whattowear.network.service.KtorWeatherApi
import quack.whattowear.network.service.WeatherApi

val appModule = module {
  factory {
    MainScreenViewModel(
      geoLocationInteractor = get(),
      mainScreenLoadingInteractor = get(),
    )
  }
  single { CoroutineScope(Dispatchers.Default + SupervisorJob()) }
  single<GeolocationInteractor> {
    GeolocationInteractorImpl(
      geolocator = MobileGeolocator(),
      locationPermissionController = LocationPermissionController(),
      scope = get()
    )
  }
  single<MainScreenLoadingInteractor> {
    MainScreenLoadingInteractorImpl(
      api = get(),
      scope = get()
    )
  }

  single {
    HttpClient {
      install(ContentNegotiation) {
        json(Json {
          prettyPrint = true
          isLenient = true
          ignoreUnknownKeys = true
        })
      }
      install(Logging) {
        level = LogLevel.ALL
      }
    }
  }

  single<WeatherApi> { KtorWeatherApi(get()) }
}