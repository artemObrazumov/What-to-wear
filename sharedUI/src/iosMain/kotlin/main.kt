import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import quack.whattowear.App

fun MainViewController(): UIViewController = ComposeUIViewController {
  App()
}
