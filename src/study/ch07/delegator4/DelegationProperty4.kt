package study.ch07.delegator1

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

open class PropertyChangeAware4 {
    protected val changeSupport = PropertyChangeSupport(this)
    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}

class Person4( val name: String, age: Int, salary: Int): PropertyChangeAware4() {
    private val observer = {
            prop: KProperty<*>, oldValue: Int, newValue: Int -> changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
    var age: Int by Delegates.observable(age, observer)
    var salary: Int by Delegates.observable(salary, observer)
}

fun main() {
    val p = Person4("Dmitry", 34, 2000)
    p.addPropertyChangeListener(PropertyChangeListener { event ->
        println("name : ${event.propertyName} changed, from ${event.oldValue} to ${event.newValue}") }
    )
    p.age = 35
    p.salary = 2100
}

// 특징 31라인 by 오른쪽에 오는 객체를 위임 객체(delegate)라고 부름
// 코틀린에는 ObservableProperty와 같은 클래스가 존재함
// 다만 PropertyChangeSupport 를 사용하는 부분은 람다로 구성해 줘야함