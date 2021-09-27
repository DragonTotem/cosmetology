package com.example.autotest;

/**
 * Author: zbt
 * Time: 2021/6/22 7:55
 * Description: This is TestClass
 */
public class TestClass {
    public void forTest() {
        for (int binCount = 0; ; ++binCount) {
            System.out.println("zzz binCount:" + binCount);
            // -1 for 1st
            if (binCount >= 7) {
                System.out.println("nnn bitCount:" + binCount);
                break;
            }
        }
    }
}
