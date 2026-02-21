package quack.whattowear.design_system.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import quack.whattowear.theme.montserratFontFamily

@Composable
fun TitleText(
  text: String,
  modifier: Modifier = Modifier,
  fontWeight: FontWeight = FontWeight.Medium,
  textAlign: TextAlign = TextAlign.Start
) {
  val fontFamily = montserratFontFamily()

  Text(
    text = text,
    modifier = modifier,
    textAlign = textAlign,
    style = MaterialTheme.typography.headlineLarge.copy(
      fontWeight = fontWeight,
      fontSize = 26.sp
    ),
    fontFamily = fontFamily,
  )
}