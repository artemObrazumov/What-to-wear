package quack.whattowear.di

import org.koin.dsl.module
import quack.whattowear.feature.main_screen.impl.ui.MainScreenViewModel

val appModule = module {
  factory {
    MainScreenViewModel()
  }
}
