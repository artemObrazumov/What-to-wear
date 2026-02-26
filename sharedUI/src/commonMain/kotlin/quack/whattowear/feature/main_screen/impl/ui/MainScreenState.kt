package quack.whattowear.feature.main_screen.impl.ui

import quack.whattowear.feature.main_screen.impl.domain.models.ClothesAdvice
import quack.whattowear.feature.main_screen.impl.ui.models.ForecastUI
import quack.whattowear.feature.main_screen.impl.ui.models.Gender

sealed interface MainScreenState {
  object Loading : MainScreenState
  object GeolocationAccessRequired : MainScreenState
  class Error(val message: String) : MainScreenState
  class Content(
    val headerSection: MainScreenSection.HeaderSection,
    val forecastSection: MainScreenSection.ForecastSection,
    val clothesSection: MainScreenSection.ClothesSection,
    val gender: Gender,
  ) : MainScreenState
}

sealed interface MainScreenSection {

  class HeaderSection(
    val localizedAddress: String,
    val formattedDate: String,
  ) : MainScreenSection

  class ForecastSection(val forecast: ForecastUI) : MainScreenSection

  sealed interface ClothesSection : MainScreenSection {
    class Content(val clothes: ClothesAdvice) : ClothesSection
    object Loading : ClothesSection
  }
}