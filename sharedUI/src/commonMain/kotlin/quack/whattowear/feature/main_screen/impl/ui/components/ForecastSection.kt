package quack.whattowear.feature.main_screen.impl.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import quack.whattowear.design_system.containers.LabeledBlock
import quack.whattowear.design_system.text.BodyText
import quack.whattowear.design_system.text.HugeText
import quack.whattowear.feature.main_screen.impl.ui.MainScreenSection
import whattowear.sharedui.generated.resources.Res
import whattowear.sharedui.generated.resources.feels_like
import whattowear.sharedui.generated.resources.humidity
import whattowear.sharedui.generated.resources.mps
import whattowear.sharedui.generated.resources.pressure
import whattowear.sharedui.generated.resources.temperature
import whattowear.sharedui.generated.resources.wind

fun LazyGridScope.forecastSection(
  data: MainScreenSection.ForecastSection,
) {
  val forecast = data.forecast
  item {
    LabeledBlock(
      title = stringResource(forecast.weatherStatus.label),
    ) {
      PictureBlock(
        icon = forecast.weatherStatus.icon,
        contentDescription = stringResource(forecast.weatherStatus.label)
      )
    }
  }
  item {
    LabeledBlock(
      title = stringResource(Res.string.temperature),
    ) {
      HugeTextBlock(forecast.temperature)
    }
  }
  item {
    LabeledBlock(
      title = stringResource(Res.string.feels_like),
    ) {
      HugeTextBlock(forecast.feelsLikeTemperature)
    }
  }
  item {
    LabeledBlock(
      title = stringResource(Res.string.wind),
    ) {
      val windFormattedString = "${forecast.wind}${stringResource(Res.string.mps)}"
      HugeTextWithLabelBlock(windFormattedString, forecast.windDirection)
    }
  }
  item {
    LabeledBlock(
      title = stringResource(Res.string.humidity),
    ) {
      HugeTextBlock(forecast.humidity)
    }
  }
  item {
    LabeledBlock(
      title = stringResource(Res.string.pressure),
    ) {
      HugeTextWithLabelBlock(forecast.pressure, stringResource(forecast.pressureMeasurement))
    }
  }

  if (forecast.predictions.isNotEmpty()) {
    item(
      span = { GridItemSpan(2) }
    ) {
      ForecastPredictions(forecast.predictions)
    }
  }
}

@Composable
private fun PictureBlock(
  icon: DrawableResource,
  contentDescription: String? = null
) {
  Image(
    modifier = Modifier.fillMaxSize(),
    painter = painterResource(icon),
    contentDescription = contentDescription
  )
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