

class Person {
    private val _attributes = hashMapOf<String, String>()
    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }

    val name: String
        get() = _attributes["name"]!!
}

fun main() {
    val p= Person()
    val data = mapOf("name" to "Dmitry", "company" to "jetbrain")
    for ((attrName, value) in data)
        p.setAttribute(attrName, value)
    println(p.name)
}
