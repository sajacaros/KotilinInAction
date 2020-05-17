package study.ch11.buildstring

fun buildString3(
        builderAction: StringBuilder.() -> Unit
): String = StringBuilder().apply(builderAction).toString();

fun main() {
    val s = buildString3 {
        this.append("Hello, ")
        append("World!")
    }
    println(s)
}