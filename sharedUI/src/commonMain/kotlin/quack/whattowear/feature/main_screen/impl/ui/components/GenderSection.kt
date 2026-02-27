package quack.whattowear.feature.main_screen.impl.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import quack.whattowear.design_system.containers.LiquidContainer
import quack.whattowear.design_system.text.BodyText
import quack.whattowear.design_system.text.TitleText
import quack.whattowear.feature.main_screen.impl.ui.models.Gender
import quack.whattowear.theme.ControlColor
import whattowear.sharedui.generated.resources.Res
import whattowear.sharedui.generated.resources.female
import whattowear.sharedui.generated.resources.gender
import whattowear.sharedui.generated.resources.male

fun LazyGridScope.genderSection(
  selectedGender: Gender,
  onGenderSelected: (Gender) -> Unit
) {
  item(
    span = { GridItemSpan(2) }
  ) {
    Column {
      TitleText(text = stringResource(Res.string.gender), textAlign = TextAlign.Start)
      LiquidContainer(
        paddingValues = PaddingValues(vertical = 10.dp, horizontal = 16.dp),
        shape = RoundedCornerShape(30.dp),
      ) {
        val genders = mapOf(
          stringResource(Res.string.male) to Gender.Male,
          stringResource(Res.string.female) to Gender.Female,
        )

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          genders.forEach { (alias, gender) ->
            val isSelected = selectedGender == gender
            Box(
              modifier = Modifier
                .weight(1f)
                .background(
                  color = if (isSelected) ControlColor else Color.Transparent,
                  shape = RoundedCornerShape(30.dp)
                )
                .clickable(
                  interactionSource = MutableInteractionSource(),
                  indication = null,
                ) { onGenderSelected(gender) }
                .padding(16.dp),
              contentAlignment = Alignment.Center
            ) {
              BodyText(
                text = alias,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
              )
            }
          }
        }
      }
    }
  }
}