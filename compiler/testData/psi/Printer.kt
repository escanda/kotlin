package com.mypackage
class MyClass {
    companion object {
        fun myFunction() {
            System.out.println("This is just a standard function")
            val myVariable = MyClass.Companion::myFunction
            ##myVariable
            ##Companion.myFunction
            ##MyClass.Companion.myFunction
            ##com.mypackage.MyClass.Companion.myFunction
        }
    }
}