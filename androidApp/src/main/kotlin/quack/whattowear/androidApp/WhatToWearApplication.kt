package quack.whattowear.androidApp

import android.app.Application
import quack.whattowear.di.initKoin

class WhatToWearApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    initKoin()
  }
}
