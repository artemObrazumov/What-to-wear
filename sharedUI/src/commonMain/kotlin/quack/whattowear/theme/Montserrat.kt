package quack.whattowear.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import whattowear.sharedui.generated.resources.Res
import whattowear.sharedui.generated.resources.montserrat_bold
import whattowear.sharedui.generated.resources.montserrat_light
import whattowear.sharedui.generated.resources.montserrat_medium
import whattowear.sharedui.generated.resources.montserrat_normal
import whattowear.sharedui.generated.resources.montserrat_semibold

@Composable
fun montserratFontFamily(): FontFamily {
  return FontFamily(
    Font(resource = Res.font.montserrat_light, weight = FontWeight.Light),
    Font(resource = Res.font.montserrat_normal, weight = FontWeight.Normal),
    Font(resource = Res.font.montserrat_medium, weight = FontWeight.Medium),
    Font(resource = Res.font.montserrat_semibold, weight = FontWeight.SemiBold),
    Font(resource = Res.font.montserrat_bold, weight = FontWeight.Bold),
  )
}