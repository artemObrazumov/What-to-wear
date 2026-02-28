package quack.whattowear.feature.main_screen.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import quack.whattowear.feature.main_screen.impl.domain.interactor.GeolocationInteractor
import quack.whattowear.feature.main_screen.impl.domain.interactor.MainScreenLoadingInteractor
import quack.whattowear.feature.main_screen.impl.ui.models.Gender
import quack.whattowear.feature.main_screen.impl.ui.models.toUI
import quack.whattowear.util.CurrentDateProvider

class MainScreenViewModel(
  private val geoLocationInteractor: GeolocationInteractor,
  private val mainScreenLoadingInteractor: MainScreenLoadingInteractor,
  private val currentDateProvider: CurrentDateProvider,
) : ViewModel() {

  private val genderFlow: MutableStateFlow<Gender> = MutableStateFlow(Gender.Male)

  private val _state: Flow<MainScreenState> = combine(
    geoLocationInteractor.isPermissionGrantedFlow,
    mainScreenLoadingInteractor.addressAndForecastFlow,
    mainScreenLoadingInteractor.clothesAdviceFlow,
    genderFlow,
  ) { isPermissionGranted, addressAndForecast, clothesAdvice, gender ->
    if (!isPermissionGranted) {
      return@combine MainScreenState.GeolocationAccessRequired
    }
    if (addressAndForecast != null) {
      val headerSection = MainScreenSection.HeaderSection(
        localizedAddress = addressAndForecast.address.localizedAddress,
        formattedDate = currentDateProvider.getCurrentDateFormatted()
      )
      val forecastSection = MainScreenSection.ForecastSection(
        forecast = addressAndForecast.forecast.toUI()
      )
      val clothesSection = if (clothesAdvice == null) {
        MainScreenSection.ClothesSection.Loading
      } else {
        MainScreenSection.ClothesSection.Content(
          clothes = clothesAdvice
        )
      }
      return@combine MainScreenState.Content(
        headerSection = headerSection,
        forecastSection = forecastSection,
        clothesSection = clothesSection,
        gender = gender,
      )
    }
    MainScreenState.Loading
  }
  val state = _state
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000L),
      initialValue = MainScreenState.Loading
    )

  init {
    viewModelScope.launch {
      val shouldTrackLocation = geoLocationInteractor.isPermissionGrantedFlow.first()
      if (shouldTrackLocation) {
        geoLocationInteractor.track()
      }
    }

    viewModelScope.launch {
      geoLocationInteractor.locationFlow.distinctUntilChanged().filterNotNull().collect { location ->
        mainScreenLoadingInteractor.loadAddressAndForecast(location)
      }
    }

    combine(
      geoLocationInteractor.locationFlow.filterNotNull(),
      genderFlow,
    ) { location, gender ->
      mainScreenLoadingInteractor.loadClothesAdvice(location, gender)
    }.shareIn(
      scope = viewModelScope,
      started = SharingStarted.Eagerly,
    )
  }

  fun onAction(action: MainScreenAction) {
    when (action) {
      MainScreenAction.ProvideGeolocationClicked -> {
        geoLocationInteractor.getPermission()
      }

      is MainScreenAction.GenderSelected -> {
        genderFlow.value = action.gender
      }

      else -> TODO("Handle actions")
    }
  }
}