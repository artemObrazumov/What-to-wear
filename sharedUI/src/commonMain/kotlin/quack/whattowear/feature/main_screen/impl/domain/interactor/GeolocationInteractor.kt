package quack.whattowear.feature.main_screen.impl.domain.interactor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import quack.whattowear.feature.geolocation.domain.model.GeoPoint

interface GeolocationInteractor {
  val isPermissionGrantedFlow: StateFlow<Boolean>
  val locationFlow: Flow<GeoPoint?>

  fun getPermission()
}