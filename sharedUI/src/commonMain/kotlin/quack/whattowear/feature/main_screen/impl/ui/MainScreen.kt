package quack.whattowear.feature.main_screen.impl.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemGestures
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import quack.whattowear.design_system.text.BodyText
import quack.whattowear.feature.main_screen.impl.domain.models.ClothesAdvice
import quack.whattowear.feature.main_screen.impl.domain.models.Forecast
import quack.whattowear.feature.main_screen.impl.domain.models.LabeledParameter
import quack.whattowear.feature.main_screen.impl.domain.models.PercentageParameter
import quack.whattowear.feature.main_screen.impl.domain.models.Prediction
import quack.whattowear.feature.main_screen.impl.domain.models.StringLabeledParameter
import quack.whattowear.feature.main_screen.impl.domain.models.Temperature
import quack.whattowear.feature.main_screen.impl.domain.models.WeatherStatus
import quack.whattowear.feature.main_screen.impl.ui.components.clothesSection
import quack.whattowear.feature.main_screen.impl.ui.components.forecastSection
import quack.whattowear.feature.main_screen.impl.ui.components.genderSection
import quack.whattowear.feature.main_screen.impl.ui.components.headerSection
import quack.whattowear.feature.main_screen.impl.ui.models.Gender
import quack.whattowear.feature.main_screen.impl.ui.models.toUI
import quack.whattowear.theme.AppTheme
import whattowear.sharedui.generated.resources.Res
import whattowear.sharedui.generated.resources.allow_geo
import whattowear.sharedui.generated.resources.geo_required
import whattowear.sharedui.generated.resources.mmHg

@Composable
fun MainScreen(
  viewModel: MainScreenViewModel = koinInject()
) {
  val state by viewModel.state.collectAsStateWithLifecycle()

  MainScreenContent(
    state = state,
    onAction = viewModel::onAction
  )
}

@Composable
fun MainScreenContent(
  state: MainScreenState,
  onAction: (MainScreenAction) -> Unit,
) {
  when (state) {
    is MainScreenState.Content -> {
      MainScreenContentState(state)
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

    MainScreenState.GeolocationAccessRequired -> {
      GeolocationRequiredState(
        onButtonClicked = { onAction(MainScreenAction.ProvideGeolocationClicked) }
      )
    }
  }
}

@Composable
fun GeolocationRequiredState(
  onButtonClicked: () -> Unit
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = 16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
  ) {
    BodyText(stringResource(Res.string.geo_required), textAlign = TextAlign.Center)
    Spacer(modifier = Modifier.height(24.dp))
    Button(
      onClick = onButtonClicked
    ) {
      BodyText(text = stringResource(Res.string.allow_geo), color = Color.Black)
    }
  }
}

@Composable
fun MainScreenContentState(
  state: MainScreenState.Content,
) {
  LazyVerticalGrid(
    horizontalArrangement = Arrangement.spacedBy(20.dp),
    verticalArrangement = Arrangement.spacedBy(20.dp),
    modifier = Modifier
      .padding(horizontal = 16.dp),
    columns = GridCells.Fixed(2)
  ) {
    item(span = { GridItemSpan(maxLineSpan) }) {
      Spacer(modifier = Modifier.height(WindowInsets.systemGestures.asPaddingValues().calculateTopPadding()))
    }
    headerSection(state.headerSection)
    forecastSection(state.forecastSection)
    genderSection { }
    clothesSection(state.clothesSection)
    item(span = { GridItemSpan(maxLineSpan) }) {
      Spacer(modifier = Modifier.height(WindowInsets.systemGestures.asPaddingValues().calculateBottomPadding()))
    }
  }
}

@Preview
@Composable
private fun Preview() {
  AppTheme {
    MainScreenContent(
      state = MainScreenState.Content(
        headerSection = MainScreenSection.HeaderSection(
          localizedAddress = "Москва",
          formattedDate = "Сегодня · 06.11.2025"
        ),
        forecastSection = MainScreenSection.ForecastSection(
          forecast = Forecast(
            temperature = Temperature(
              value = 18,
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
              value = 7540
            ),
            status = WeatherStatus.Sunny,
            predictions = List(4) {
              Prediction(
                weatherStatus = WeatherStatus.Sunny,
                temperature = Temperature(
                  value = 18,
                  sign = Temperature.Sign.PLUS
                )
              )
            }
          ).toUI()
        ),
        clothesSection = MainScreenSection.ClothesSection.Content(
          clothes = ClothesAdvice(
            headwear = "Шапка",
            outerwear = "Пальто",
            footwear = "Ботинки",
            upperBodyClothing = "Свитер",
            lowerBodyClothing = "Джинсы",
            accessories = "Шапка, Шарф"
          )
        ),
        gender = Gender.Male,
      ),
      onAction = {}
    )
  }
}