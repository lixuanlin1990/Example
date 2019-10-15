package com.example.lixlop.javalib;

import java.util.ArrayList;
import java.util.List;

public class MyClass {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            list.add("" + i);
        }
        List<String> list1 = list.subList(0,5);
        System.out.println(list1);
        System.out.println(list);
    }
}
