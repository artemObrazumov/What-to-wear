package quack.whattowear.feature.main_screen.impl.ui

import quack.whattowear.feature.main_screen.impl.ui.models.Gender

sealed interface MainScreenAction {
  object AddressClicked : MainScreenAction
  object ProvideGeolocationClicked : MainScreenAction
  class GenderSelected(val gender: Gender) : MainScreenAction
}