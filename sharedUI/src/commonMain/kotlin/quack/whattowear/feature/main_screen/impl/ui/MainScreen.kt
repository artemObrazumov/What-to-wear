package quack.whattowear.feature.main_screen.impl.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.fletchmckee.liquid.LiquidState
import io.github.fletchmckee.liquid.rememberLiquidState
import quack.whattowear.feature.main_screen.impl.domain.models.Forecast
import quack.whattowear.feature.main_screen.impl.domain.models.LabeledParameter
import quack.whattowear.feature.main_screen.impl.domain.models.PercentageParameter
import quack.whattowear.feature.main_screen.impl.domain.models.StringLabeledParameter
import quack.whattowear.feature.main_screen.impl.domain.models.Temperature
import quack.whattowear.feature.main_screen.impl.ui.components.HeaderSection
import quack.whattowear.feature.main_screen.impl.ui.components.forecastSection
import quack.whattowear.feature.main_screen.impl.ui.models.toUI
import quack.whattowear.theme.AppTheme
import whattowear.sharedui.generated.resources.Res
import whattowear.sharedui.generated.resources.mmHg

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
  val liquidState = rememberLiquidState()

  when (state) {
    is MainScreenState.Content -> {
      MainScreenContentState(state, liquidState)
    }

    is MainScreenState.Error -> {}

    MainScreenState.Loading -> {
      Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
      ) {
        CircularProgressIndicator()
      }
    }
  }
}

@Composable
fun MainScreenContentState(
  state: MainScreenState.Content,
  liquidState: LiquidState,
) {
  LazyVerticalGrid(
    columns = GridCells.Fixed(2)
  ) {
    item(
      span = { GridItemSpan(2) }
    ) {
      HeaderSection(state.headerSection)
    }
    forecastSection(state.forecastSection, liquidState)
  }
}

@Preview
@Composable
private fun Preview() {
  AppTheme {
    MainScreenScreen(
      state = MainScreenState.Content(
        headerSection = MainScreenSection.HeaderSection(
          localizedAddress = "Москва",
          formattedDate = "Сегодня · 06.11.2025"
        ),
        forecastSection = MainScreenSection.ForecastSection(
          forecast = Forecast(
            temperature = Temperature(
              value = 8,
              sign = Temperature.Sign.PLUS
            ),
            feelsLikeTemperature = Temperature(
              value = 5,
              sign = Temperature.Sign.PLUS
            ),
            wind = StringLabeledParameter(
              label = "ЮЗ",
              value = 10
            ),
            humidity = PercentageParameter(
              value = 72
            ),
            pressure = LabeledParameter(
              label = Res.string.mmHg,
              value = 754
            )
          ).toUI()
        ),
      ),
      onAction = {}
    )
  }
}