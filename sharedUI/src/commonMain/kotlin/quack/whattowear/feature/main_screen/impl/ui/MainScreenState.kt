package quack.whattowear.feature.main_screen.impl.ui

import quack.whattowear.feature.main_screen.impl.ui.models.ForecastUI

sealed interface MainScreenState {
  object Loading : MainScreenState
  class Error(val message: String) : MainScreenState
  class Content(
    val headerSection: MainScreenSection.HeaderSection,
    val forecastSection: MainScreenSection.ForecastSection,
  ) : MainScreenState
}

sealed interface MainScreenSection {

  class HeaderSection(
    val localizedAddress: String,
    val formattedDate: String,
  ) : MainScreenSection

  class ForecastSection(val forecast: ForecastUI) : MainScreenSection
}