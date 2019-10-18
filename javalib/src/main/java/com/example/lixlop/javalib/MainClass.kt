package com.example.lixlop.javalib

import java.util.*

class MainClass {
    //    fun main(args: Array<String>) {
//        println("${(0..6).random()}")
//    }
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var activitys: Deque<TestClass> = LinkedList<TestClass>()
            val a = TestClass(0)
            val b = TestClass(1)
            activitys.offerFirst(a)
            activitys.offerFirst(b)
            activitys.offerFirst(a)
            activitys.removeLastOccurrence(a)
        }
    }
}