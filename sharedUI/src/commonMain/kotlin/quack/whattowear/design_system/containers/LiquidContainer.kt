package quack.whattowear.design_system.containers

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import quack.whattowear.theme.LiquidBackgroundBottom
import quack.whattowear.theme.LiquidBackgroundTop
import quack.whattowear.theme.LiquidBorderEdge
import quack.whattowear.theme.LiquidBorderMiddle
import quack.whattowear.theme.ShadowColor

@Composable
fun LiquidContainer(
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit
) {
  Box(
    modifier = modifier
      .shadow(
        elevation = 8.dp,
        spotColor = ShadowColor,
        ambientColor = ShadowColor,
        shape = RoundedCornerShape(16.dp)
      )
      .background(
        brush = Brush.linearGradient(listOf(LiquidBackgroundTop, LiquidBackgroundBottom)),
        shape = RoundedCornerShape(16.dp)
      )
      .border(
        width = 2.dp,
        brush = Brush.linearGradient(listOf(LiquidBorderEdge, LiquidBorderMiddle, LiquidBorderEdge)),
        shape = RoundedCornerShape(16.dp)
      )
      .padding(12.dp)
  ) {
    content()
  }
}
