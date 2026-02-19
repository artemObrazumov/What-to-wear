package quack.whattowear.feature.main_screen.impl.ui.components

import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import io.github.fletchmckee.liquid.LiquidState
import org.jetbrains.compose.resources.stringResource
import quack.whattowear.design_system.containers.LabeledBlock
import quack.whattowear.design_system.text.BodyText
import quack.whattowear.design_system.text.HugeText
import quack.whattowear.feature.main_screen.impl.ui.MainScreenSection
import whattowear.sharedui.generated.resources.Res
import whattowear.sharedui.generated.resources.feels_like
import whattowear.sharedui.generated.resources.humidity
import whattowear.sharedui.generated.resources.pressure
import whattowear.sharedui.generated.resources.temperature
import whattowear.sharedui.generated.resources.wind

fun LazyGridScope.forecastSection(
  data: MainScreenSection.ForecastSection,
  liquidState: LiquidState,
) {
  val forecast = data.forecast
  item {
    LabeledBlock(
      title = stringResource(Res.string.temperature),
      liquidState = liquidState
    ) {

    }
  }
  item {
    LabeledBlock(
      title = stringResource(Res.string.temperature),
      liquidState = liquidState
    ) {
      HugeTextBlock(forecast.temperature)
    }
  }
  item {
    LabeledBlock(
      title = stringResource(Res.string.feels_like),
      liquidState = liquidState
    ) {
      HugeTextBlock(forecast.feelsLikeTemperature)
    }
  }
  item {
    LabeledBlock(
      title = stringResource(Res.string.wind),
      liquidState = liquidState
    ) {
      HugeTextWithLabelBlock(forecast.wind, forecast.windDirection)
    }
  }
  item {
    LabeledBlock(
      title = stringResource(Res.string.humidity),
      liquidState = liquidState
    ) {
      HugeTextBlock(forecast.humidity)
    }
  }
  item {
    LabeledBlock(
      title = stringResource(Res.string.pressure),
      liquidState = liquidState
    ) {
      HugeTextWithLabelBlock(forecast.pressure, stringResource(forecast.pressureMeasurement))
    }
  }
}

// TODO: add forecast status image
@Composable
private fun PictureBlock() {

}

@Composable
private fun HugeTextBlock(
  text: String
) {
  HugeText(text, textAlign = TextAlign.Center)
}

@Composable
private fun HugeTextWithLabelBlock(
  hugeText: String,
  label: String
) {
  HugeTextBlock(hugeText)
  BodyText(label, textAlign = TextAlign.Center)
}