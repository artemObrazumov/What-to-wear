package quack.whattowear.feature.main_screen.impl.domain.models

import org.jetbrains.compose.resources.StringResource

class LabeledParameter(
  val label: StringResource,
  val value: Int,
)

class StringLabeledParameter(
  val label: String,
  val value: Int,
)