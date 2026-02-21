import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.compose.multiplatform)
  alias(libs.plugins.android.kmp.library)
  alias(libs.plugins.kotlinx.serialization)
  alias(libs.plugins.metro)
}

kotlin {
  androidTarget() //We need the deprecated target to have working previews

  iosX64()
  iosArm64()
  iosSimulatorArm64()

  sourceSets {
    commonMain.dependencies {
      api(libs.compose.runtime)
      api(libs.compose.ui)
      api(libs.compose.foundation)
      api(libs.compose.resources)
      api(libs.compose.ui.tooling.preview)
      api(libs.compose.material3)
      implementation(libs.compose.material3.icons.extended)
      implementation(libs.kotlinx.coroutines.core)
      implementation(libs.ktor.client.core)
      implementation(libs.ktor.client.content.negotiation)
      implementation(libs.ktor.client.serialization)
      implementation(libs.ktor.serialization.json)
      implementation(libs.ktor.client.logging)
      implementation(libs.androidx.lifecycle.viewmodel)
      implementation(libs.androidx.lifecycle.runtime)
      implementation(libs.compose.nav3)
      implementation(libs.kotlinx.serialization.json)
      implementation(libs.multiplatformSettings)
    }

    commonTest.dependencies {
      implementation(kotlin("test"))
      implementation(libs.compose.ui.test)
      implementation(libs.kotlinx.coroutines.test)
    }

    androidMain.dependencies {
      implementation(libs.kotlinx.coroutines.android)
      implementation(libs.ktor.client.okhttp)
    }

    iosMain.dependencies {
      implementation(libs.ktor.client.darwin)
    }

  }

  targets
    .withType<KotlinNativeTarget>()
    .matching { it.konanTarget.family.isAppleFamily }
    .configureEach {
      binaries {
        framework {
          baseName = "SharedUI"
          isStatic = true
        }
      }
    }
}

dependencies {
  debugImplementation(libs.compose.ui.tooling)
}
android {
  namespace = "quack.whattowear"
  compileSdk = 36
  defaultConfig {
    minSdk = 23
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
}
