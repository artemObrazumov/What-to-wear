package quack.whattowear.feature.main_screen.impl.ui

sealed interface MainScreenAction {
  object AddressClicked : MainScreenAction
  object ProvideGeolocationClicked : MainScreenAction
}