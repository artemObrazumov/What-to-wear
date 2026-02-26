package quack.whattowear.di

import dev.jordond.compass.geolocation.MobileGeolocator
import dev.jordond.compass.permissions.LocationPermissionController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module
import quack.whattowear.feature.main_screen.impl.data.GeolocationInteractorImpl
import quack.whattowear.feature.main_screen.impl.domain.interactor.GeolocationInteractor
import quack.whattowear.feature.main_screen.impl.ui.MainScreenViewModel

val appModule = module {
  factory {
    MainScreenViewModel(
      geoLocationInteractor = get()
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
}