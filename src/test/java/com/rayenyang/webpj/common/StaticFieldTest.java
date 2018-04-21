package com.rayenyang.webpj.common;

/**
 * 静态变量加载顺序测试
 * Created by rayenyang on 2017/1/13.
 */
public class StaticFieldTest {
    public static void main(String[] args) {
        /**
         construct T:5
         construct T:2
         construct T:3
         construct T:1
         construct T:4
         */
        final Bridge bridge = new Bridge();
        bridge.print();
    }
}

class Bridge{
    private T t6;
    private T t1 = new T(1);
    private static T t3;
    private static T t5 = new T(5);
    private static T t2 = new T(2);
    private static T t7;

    static{
        System.out.println("static");
        t3 = new T(3);
    }
    {
        System.out.println("non static");
    }

    public Bridge() {
        new T(4);
    }

    public void print(){
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
        System.out.println(t5);
        System.out.println(t6);
        System.out.println(t7);
    }
}

class T {
    private int i;
    public T(int i) {
        System.out.println("construct T:" + i);
        this.i = i;
    }

    @Override
    public String toString() {
        return "T{" +
                "i=" + i +
                '}';
    }
}
