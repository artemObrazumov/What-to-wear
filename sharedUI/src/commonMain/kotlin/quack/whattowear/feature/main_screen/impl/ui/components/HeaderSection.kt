package quack.whattowear.feature.main_screen.impl.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import quack.whattowear.design_system.text.HeaderText
import quack.whattowear.design_system.text.TitleText
import quack.whattowear.feature.main_screen.impl.ui.MainScreenSection

@Composable
fun HeaderSection(
  data: MainScreenSection.HeaderSection,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(
        Icons.Default.Pin,
        contentDescription = null
      )
      Spacer(modifier = Modifier.width(8.dp))
      HeaderText(text = data.localizedAddress, textAlign = TextAlign.Center)
    }
    Spacer(modifier = Modifier.height(8.dp))
    TitleText(text = data.formattedDate, textAlign = TextAlign.Start)
  }
}
