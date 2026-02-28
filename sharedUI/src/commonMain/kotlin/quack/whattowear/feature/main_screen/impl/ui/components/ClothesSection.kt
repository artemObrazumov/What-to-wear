package quack.whattowear.feature.main_screen.impl.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import quack.whattowear.design_system.containers.LabeledBlock
import quack.whattowear.design_system.text.BodyText
import quack.whattowear.design_system.text.TitleText
import quack.whattowear.feature.main_screen.impl.ui.MainScreenSection
import whattowear.sharedui.generated.resources.Res
import whattowear.sharedui.generated.resources.accessories
import whattowear.sharedui.generated.resources.clothes
import whattowear.sharedui.generated.resources.footwear
import whattowear.sharedui.generated.resources.lowerBodyClothing
import whattowear.sharedui.generated.resources.outerwear
import whattowear.sharedui.generated.resources.upperBodyClothing

fun LazyGridScope.clothesSection(
  data: MainScreenSection.ClothesSection,
) {
  when (data) {
    is MainScreenSection.ClothesSection.Content -> {
      item(
        span = { GridItemSpan(2) }
      ) {
        TitleText(text = stringResource(Res.string.clothes), textAlign = TextAlign.Start)
      }
      item {
        LabeledBlock(
          title = stringResource(Res.string.outerwear),
        ) {
          HeaderTextBlock(data.clothes.outerwear)
        }
      }
      item {
        LabeledBlock(
          title = stringResource(Res.string.upperBodyClothing),
        ) {
          HeaderTextBlock(data.clothes.upperBodyClothing)
        }
      }
      item {
        LabeledBlock(
          title = stringResource(Res.string.lowerBodyClothing),
        ) {
          HeaderTextBlock(data.clothes.lowerBodyClothing)
        }
      }
      item {
        LabeledBlock(
          title = stringResource(Res.string.footwear),
        ) {
          HeaderTextBlock(data.clothes.footwear)
        }
      }
      item(
        span = { GridItemSpan(2) }
      ) {
        LabeledBlock(
          title = stringResource(Res.string.accessories),
          ratio = 2f
        ) {
          HeaderTextBlock(data.clothes.accessories)
        }
      }
    }

    MainScreenSection.ClothesSection.Loading -> {
      item(
        span = { GridItemSpan(2) }
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          CircularProgressIndicator(
            modifier = Modifier
              .size(36.dp)
          )
        }
      }
    }
  }
}

@Composable
private fun HeaderTextBlock(
  text: String
) {
  BodyText(text, textAlign = TextAlign.Center, fontWeight = FontWeight.Light)
}
