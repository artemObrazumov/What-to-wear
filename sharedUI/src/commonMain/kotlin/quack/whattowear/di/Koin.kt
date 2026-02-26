package quack.whattowear.di

import org.koin.core.context.startKoin

fun initKoin() {
  startKoin {
    modules(appModule)
  }
}
