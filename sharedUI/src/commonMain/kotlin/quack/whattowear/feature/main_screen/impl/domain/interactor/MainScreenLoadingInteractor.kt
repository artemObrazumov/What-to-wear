package quack.whattowear.feature.main_screen.impl.domain.interactor

import quack.whattowear.feature.main_screen.impl.domain.models.AddressAndForecast

interface MainScreenLoadingInteractor {
  suspend fun loadAddressAndForecast(): AddressAndForecast
}