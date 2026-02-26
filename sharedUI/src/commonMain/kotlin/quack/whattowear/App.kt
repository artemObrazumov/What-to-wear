package quack.whattowear

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import quack.whattowear.feature.main_screen.impl.ui.MainScreen
import quack.whattowear.theme.AppTheme

@Preview
@Composable
fun App() {
  AppTheme {
    MainScreen()
  }
}