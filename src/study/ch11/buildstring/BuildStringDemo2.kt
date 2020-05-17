package study.ch11.buildstring

fun buildString2(
        builderAction: StringBuilder.() -> Unit
): String {
    val sb = StringBuilder()
    builderAction(sb)
    return sb.toString()
}

fun main() {
    val s = buildString2 {
        this.append("Hello, ")
        append("World!")
    }
    println(s)
}