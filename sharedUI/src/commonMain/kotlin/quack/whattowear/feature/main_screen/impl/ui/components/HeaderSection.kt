package quack.whattowear.feature.main_screen.impl.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import quack.whattowear.design_system.text.HeaderText
import quack.whattowear.design_system.text.TitleText
import quack.whattowear.feature.main_screen.impl.ui.MainScreenSection

fun LazyGridScope.headerSection(
  data: MainScreenSection.HeaderSection,
  modifier: Modifier = Modifier
) {
  item(
    span = { GridItemSpan(2) }
  ) {
    Column(modifier = modifier) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
          Icons.Default.LocationOn,
          contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        HeaderText(text = data.localizedAddress, textAlign = TextAlign.Center)
      }
      Spacer(modifier = Modifier.height(8.dp))
      TitleText(text = data.formattedDate, textAlign = TextAlign.Start)
    }
  }
}
