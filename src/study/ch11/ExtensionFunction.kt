package study.ch11

fun main() {
    val appendExcl : StringBuilder.() -> Unit =
            { this.append("!") }

    val stringBuilder = StringBuilder("Hi")
    stringBuilder.appendExcl()
    println(stringBuilder)
}
