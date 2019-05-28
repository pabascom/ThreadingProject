package phil.homework.threadingproject.calculator

class Calculation(val addition: Addition) {

    fun addTen(a: Int, b: Int) = addition.add(a,b) + 10

    fun subtract(a: Int, b: Int): Int = a - b

}