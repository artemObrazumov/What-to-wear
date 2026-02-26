package quack.whattowear.feature.main_screen.impl.domain.interactor

import kotlinx.coroutines.flow.StateFlow
import quack.whattowear.feature.geolocation.domain.model.GeoPoint
import quack.whattowear.feature.main_screen.impl.domain.models.AddressAndForecast
import quack.whattowear.feature.main_screen.impl.domain.models.ClothesAdvice
import quack.whattowear.feature.main_screen.impl.ui.models.Gender

interface MainScreenLoadingInteractor {
  val addressAndForecastFlow: StateFlow<AddressAndForecast?>
  val clothesAdviceFlow: StateFlow<ClothesAdvice?>

  fun loadAddressAndForecast(location: GeoPoint)
  fun loadClothesAdvice(location: GeoPoint, gender: Gender)
}