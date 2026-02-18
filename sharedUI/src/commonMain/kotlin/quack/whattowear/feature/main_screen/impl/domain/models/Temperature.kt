package quack.whattowear.feature.main_screen.impl.domain.models

class Temperature(
  val value: Int,
  val sign: Sign,
) {
  enum class Sign(val value: String) {
    PLUS("+"),
    MINUS("-"),
    EMPTY(""),
  }

  override fun toString(): String {
    return listOf(sign.value, value, "℃").joinToString("")
  }
}