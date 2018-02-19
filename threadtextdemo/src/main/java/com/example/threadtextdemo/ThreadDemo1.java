package com.example.threadtextdemo;

/**
 * Created by Kong on 2018/2/19.
 * 同步代码实例
 */
public class ThreadDemo1 {

    static class Count {
        static int value;
        Object lock = new Object();

        /**
         * 无同步代码的方法
         */
        public void add() throws InterruptedException {
            int temp = value;
            Thread.sleep(100);
            value = temp + 1;
            System.out.println("Value is:" + value);
        }

        /**
         * 同步代码块
         */
        public void add1() throws InterruptedException {
            synchronized (lock) {
                int temp = value;
                Thread.sleep(100);
                value = temp + 1;
                System.out.println("Value is:" + value);
            }
        }

        /**
         * 同步方法
         */
        public synchronized void add2() throws InterruptedException {
            int temp = value;
            Thread.sleep(100);
            value = temp + 1;
            System.out.println("Value is:" + value);
        }

        /**
         * 同步静态方法
         * synchronized 传入的是当前类对象的实例
         */
        public synchronized static void add3() throws InterruptedException {
            int temp = value;
            Thread.sleep(100);
            value = temp + 1;
            System.out.println("Value is:" + value);
        }

        /**
         * 同步类
         * synchronized 与 add3 方法一样，传入的是当前类对象的实例
         */
        public void add4() throws InterruptedException {
            synchronized (Count.class) {
                int temp = value;
                Thread.sleep(100);
                value = temp + 1;
                System.out.println("Value is:" + value);
            }
        }

    }

    public static void main(String[] args) {
        new Task1().start();
        new Task1().start();
    }

    static class Task extends Thread {
        static Count count = new Count();

        @Override
        public void run() {
            try {
//                count.add();
                count.add1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class Task1 extends Thread {
        @Override
        public void run() {
            Count count = new Count();
            try {
                count.add3();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
