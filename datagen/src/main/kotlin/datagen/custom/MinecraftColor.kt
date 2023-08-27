package datagen.custom

enum class MinecraftColor(val code: String) {
  BLACK("black"),
  GRAY("gray"),
  LIGHT_GRAY("light_gray"),
  WHITE("white"),
  BROWN("brown"),
  BLUE("blue"),
  CYAN("cyan"),
  LIGHT_BLUE("light_blue"),
  LIME("lime"),
  GREEN("green"),
  YELLOW("yellow"),
  ORANGE("orange"),
  RED("red"),
  PINK("pink"),
  MAGENTA("magenta"),
  PURPLE("purple");

  fun getDye() = "minecraft:${code}_dye"
  override fun toString() = code

  companion object {
    fun forEach(action: (MinecraftColor) -> Unit) {
      for (color in values()) {
        action(color)
      }
    }
  }
}