package quack.whattowear.feature.main_screen.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import quack.whattowear.feature.main_screen.impl.domain.interactor.GeolocationInteractor

class MainScreenViewModel(
  private val geoLocationInteractor: GeolocationInteractor,
) : ViewModel() {

  private val _state: Flow<MainScreenState> = combine(
    geoLocationInteractor.isPermissionGrantedFlow,
    geoLocationInteractor.locationFlow,
  ) { isPermissionGranted, location ->
    if (!isPermissionGranted) {
      return@combine MainScreenState.GeolocationAccessRequired
    }
    MainScreenState.Loading
  }
  val state = _state
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000L),
      initialValue = MainScreenState.Loading
    )

  fun onAction(action: MainScreenAction) {
    when (action) {
      MainScreenAction.ProvideGeolocationClicked -> {
        geoLocationInteractor.getPermission()
      }
      else -> TODO("Handle actions")
    }
  }
}