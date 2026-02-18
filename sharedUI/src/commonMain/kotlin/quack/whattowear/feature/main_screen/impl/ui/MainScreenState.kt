package quack.whattowear.feature.main_screen.impl.ui

import quack.whattowear.feature.main_screen.impl.domain.models.Forecast

sealed interface MainScreenState {
  object Loading : MainScreenState
  class Error(val message: String) : MainScreenState
  class Content(val sections: List<MainScreenSection>) : MainScreenState
}

sealed interface MainScreenSection {

  class HeaderSection(
    val localizedAddress: String,
    val formattedDate: String,
  ) : MainScreenSection

  class ForecastSection(val forecast: Forecast) : MainScreenSection
}