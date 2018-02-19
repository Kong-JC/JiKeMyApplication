package com.example.threadtextdemo;

/**
 * Created by Kong on 2018/2/19.
 * 演示多线程数据同步问题
 */
public class ThreadDemo2 {

    static boolean isWaiting = true;

    synchronized static void get() throws InterruptedException {
        if (isWaiting) {
            System.out.println("Waiting");
            ThreadDemo2.class.wait();
            System.out.println("Ready and Get");
        } else {
            System.out.println("Get");
        }
    }

    synchronized static void set() throws InterruptedException {
        if (isWaiting) {
            System.out.println("Prepare");
            Thread.sleep(100);
            ThreadDemo2.class.notify();
            System.out.println("Ready and Notify");
        } else {
            System.out.println("Ready");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Task1().start();
        new Task2().start();
        ThreadDemo2.class.wait(); // 会报错，必须在同步代码块中执行
    }

    static class Task1 extends Thread {
        @Override
        public void run() {
            try {
                get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class Task2 extends Thread {
        @Override
        public void run() {
            try {
                set();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
