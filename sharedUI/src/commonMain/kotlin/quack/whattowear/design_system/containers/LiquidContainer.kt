package quack.whattowear.design_system.containers

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import quack.whattowear.theme.LiquidBackgroundBottom
import quack.whattowear.theme.LiquidBackgroundTop
import quack.whattowear.theme.LiquidBorderEdge
import quack.whattowear.theme.LiquidBorderMiddle
import quack.whattowear.theme.ShadowColor

@Composable
fun LiquidContainer(
  modifier: Modifier = Modifier,
  paddingValues: PaddingValues = PaddingValues(12.dp),
  shape: Shape = RoundedCornerShape(16.dp),
  content: @Composable () -> Unit,
) {
  Box(
    modifier = modifier
      .shadow(
        elevation = 8.dp,
        spotColor = ShadowColor,
        ambientColor = ShadowColor,
        shape = shape,
      )
      .background(
        brush = Brush.linearGradient(listOf(LiquidBackgroundTop, LiquidBackgroundBottom)),
        shape = shape,
      )
      .border(
        width = 2.dp,
        brush = Brush.linearGradient(listOf(LiquidBorderEdge, LiquidBorderMiddle, LiquidBorderEdge)),
        shape = shape,
      )
      .padding(paddingValues)
  ) {
    content()
  }
}
