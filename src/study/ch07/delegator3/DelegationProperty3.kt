package study.ch07.delegator1

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.reflect.KProperty

open class PropertyChangeAware3 {
    protected val changeSupport = PropertyChangeSupport(this)
    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}

class ObservableProperty3( // property name이 빠짐, getter와 setter 함수의 prop에 포함됨
    var propValue: Int,
    val changeSupport: PropertyChangeSupport
) {
    operator fun getValue(p:Person3, prop:KProperty<*>): Int = propValue // 관례 getter로 정의해야함
    operator fun setValue(p:Person3, prop:KProperty<*>, newValue: Int) {  // 관례 setter로 정의해야함
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}

class Person3( val name: String, age: Int, salary: Int): PropertyChangeAware3() {
    var age: Int by ObservableProperty3(age, changeSupport)
    var salary: Int by ObservableProperty3(salary, changeSupport)
}

fun main() {
    val p = Person3("Dmitry", 34, 2000)
    p.addPropertyChangeListener(PropertyChangeListener { event ->
        println("name : ${event.propertyName} changed, from ${event.oldValue} to ${event.newValue}") }
    )
    p.age = 35
    p.salary = 2100
}

// 특징 31라인 by 오른쪽에 오는 객체를 위임 객체(delegate)라고 부름
// 코틀린에는 ObservableProperty와 같은 클래스가 존재함
// 다만 PropertyChangeSupport 를 사용하는 부분은 람다로 구성해 줘야함