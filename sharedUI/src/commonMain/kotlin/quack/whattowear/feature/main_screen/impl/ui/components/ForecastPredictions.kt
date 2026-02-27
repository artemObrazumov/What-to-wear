package quack.whattowear.feature.main_screen.impl.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import quack.whattowear.design_system.containers.LiquidContainer
import quack.whattowear.design_system.text.BodyText
import quack.whattowear.feature.main_screen.impl.ui.models.PredictionUI
import quack.whattowear.theme.DividerColor

@Composable
fun ForecastPredictions(
  predictions: List<PredictionUI>,
) {
  LiquidContainer(
    paddingValues = PaddingValues(0.dp)
  ) {
    LazyRow(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 36.dp)
    ) {
      itemsIndexed(predictions) { index, prediction ->
        Row(
          modifier = Modifier.height(IntrinsicSize.Max),
          verticalAlignment = Alignment.CenterVertically
        ) {
          ForecastPredictionItem(
            prediction = prediction,
            modifier = Modifier.width(120.dp).fillMaxHeight()
          )
          if (index < predictions.lastIndex) {
            VerticalDivider(
              modifier = Modifier
                .width(1.dp)
                .fillMaxHeight(),
              color = DividerColor,
            )
          }
        }
      }
    }
  }
}

@Composable
fun ForecastPredictionItem(
  prediction: PredictionUI,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(5.dp, alignment = Alignment.CenterVertically)
  ) {
    BodyText(prediction.time)
    Image(
      modifier = Modifier.size(56.dp),
      painter = painterResource(prediction.weatherStatus.icon),
      contentDescription = stringResource(prediction.weatherStatus.label)
    )
    BodyText(prediction.temperature, fontWeight = FontWeight.Medium)
  }
}