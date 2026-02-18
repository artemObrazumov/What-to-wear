package quack.whattowear.feature.main_screen.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import quack.whattowear.theme.AppTheme

@Composable
fun MainScreenRoot(
  viewModel: MainScreenViewModel = viewModel()
) {
  val state by viewModel.state.collectAsStateWithLifecycle()

  MainScreenScreen(
    state = state,
    onAction = viewModel::onAction
  )
}

@Composable
fun MainScreenScreen(
  state: MainScreenState,
  onAction: (MainScreenAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
  AppTheme {
    MainScreenScreen(
      state = MainScreenState.Loading,
      onAction = {}
    )
  }
}