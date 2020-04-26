

class Person2 {
    private val _attributes = hashMapOf<String, String>()
    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }

    val name: String by _attributes
    val company: String by _attributes
}

fun main() {
    val p= Person2()
    val data = mapOf("name" to "Dmitry", "company" to "jetbrain")
    for ((attrName, value) in data)
        p.setAttribute(attrName, value)
    println(p.name)
    println(p.company)
}
