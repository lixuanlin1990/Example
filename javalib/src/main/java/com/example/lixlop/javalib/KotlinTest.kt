package com.example.lixlop.javalib

/**扩展函数**/
fun User.Print() {
    print("用户名 $name")
}

class User(var name: String) {
    companion object {
        fun test() {

        }
    }
}

fun User?.toString2(): String {
    if (this == null)
        return "null2"
    return toString()
}

fun User.Companion.test2() {

}

fun main(arg: Array<String>) {
    repeat(100) {

    }
}