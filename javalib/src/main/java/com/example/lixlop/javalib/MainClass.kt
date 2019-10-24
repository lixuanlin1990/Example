package com.example.lixlop.javalib

class MainClass {

    constructor(test: String){
        println(1)
    }

    init {
        println(2)
    }
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            MainClass("test")
        }
    }
}