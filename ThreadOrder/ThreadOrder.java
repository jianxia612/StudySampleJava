package com.hphz.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试线程有序执行
 * 使用共享变量的方式：代码如下
 * 利用原子递增控制线程准入顺序
 */
public class ThreadOrder {
    private  static AtomicInteger count =new AtomicInteger(0);
    private static Holder holder = new Holder();
    public static void main(String[] args) throws InterruptedException {
        //atomicIntegerThreadOrder();//使用原子类型实现线程顺序执行
        //volatileThreadOrder();//使用 volatile变量实现线程顺序执行
       //joinMethodThreadOrder(); //线程的join方法实现线程顺序执行
        threadExecutorSubmitOrder();//使用线程池的submit方法实现线程顺序执行
    }

    /**
     * 使用原子类型实现线程顺序执行
     * @throws InterruptedException
     */
    public  static void atomicIntegerThreadOrder() throws InterruptedException{
        AtomicOrderTask task1 = new AtomicOrderTask(count, 0);
        AtomicOrderTask task2 = new AtomicOrderTask(count, 1);
        AtomicOrderTask task3 = new AtomicOrderTask(count, 2);
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);
        thread1.setDaemon(true);
        thread2.setDaemon(true);
        thread3.setDaemon(true);
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(1 * 1000);
        //每次执行完毕后重新清0
        if (count.get()==3){
            count =new AtomicInteger(0);
        }
    }

    /**
     *volatile变量在各个线程中是一致的
     * @throws InterruptedException
     */
    public  static void volatileThreadOrder() throws InterruptedException{
        VolatileOrderTask task1 = new VolatileOrderTask(holder , 0);
        VolatileOrderTask task2 = new VolatileOrderTask(holder , 1);
        VolatileOrderTask task3 = new VolatileOrderTask(holder , 2);
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);
        thread1.setDaemon(true);
        thread2.setDaemon(true);
        thread3.setDaemon(true);
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(1 * 1000);
    }

    /**
     * Thread的join方法实现线程顺序执行
     */
    public  static void joinMethodThreadOrder(){

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1: "+Thread.currentThread().getName());
            }
        }, "T1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread1.join();//确定thread1执行完毕
                    System.out.println("thread2: "+Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T2");
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread2.join();//确定thread2执行完毕
                    System.out.println("thread3: "+Thread.currentThread().getName());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T3");
        thread1.start();
        thread2.start();
        thread3.start();
    }

    /**
     * 线程池的submit方法实现线程顺序执行
     */
    public static void threadExecutorSubmitOrder() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("Thread1");
                System.out.println("thread1: " + Thread.currentThread().getName());
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("Thread2");
                System.out.println("thread2: " + Thread.currentThread().getName());
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("Thread3");
                System.out.println("thread3: " + Thread.currentThread().getName());
            }
        });

        //通过线程池实现线程顺序执行
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(thread1);
        executor.submit(thread2);
        executor.submit(thread3);
        executor.shutdown();
    }
}