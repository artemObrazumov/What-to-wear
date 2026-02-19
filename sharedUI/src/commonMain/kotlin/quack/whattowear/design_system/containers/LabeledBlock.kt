package quack.whattowear.design_system.containers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import io.github.fletchmckee.liquid.LiquidState
import quack.whattowear.design_system.text.BodyText

@Composable
fun LabeledBlock(
  title: String,
  liquidState: LiquidState,
  content: @Composable ColumnScope.() -> Unit
) {
  LiquidContainer(
    liquidState = liquidState,
    modifier = Modifier
      .fillMaxWidth()
      .aspectRatio(1f)
  ) {
    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      BodyText(text = title, textAlign = TextAlign.Center)
      Spacer(modifier = Modifier.weight(1f))
      Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Spacer(modifier = Modifier.weight(3f))
        content()
        Spacer(modifier = Modifier.weight(5f))
      }
    }
  }
}