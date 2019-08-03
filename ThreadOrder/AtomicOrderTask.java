package com.hphz.test;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicOrderTask implements Runnable {

    private AtomicInteger count;
    private int order;
    public AtomicOrderTask(AtomicInteger count, int order) {
        this.count = count;
        this.order = order;
    }

    @Override
    public void run(){
        /*
        while (true) {
        }
        */
        if (count.get() % 3 == order) {
            System.out.println(Thread.currentThread().getName() + " ===== "+ order);
            System.out.println("count: "+count);
            count.incrementAndGet();
            //System.out.println("count.get(): "+count.get());
        }
    }
}
