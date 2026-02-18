package quack.whattowear.feature.main_screen.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class MainScreenViewModel : ViewModel() {

  private val _state: StateFlow<MainScreenState> = MutableStateFlow(MainScreenState.Loading)
  val state = _state
    .onStart {

    }
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000L),
      initialValue = MainScreenState.Loading
    )

  fun onAction(action: MainScreenAction) {
    when (action) {
      else -> TODO("Handle actions")
    }
  }
}