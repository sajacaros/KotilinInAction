package study.ch07

fun main() {
    val mutableList = arrayListOf(1,2)
    mutableList += 3

    var immutableList = listOf(1,2)
    immutableList += 3

    println(mutableList)
    println(immutableList)
}