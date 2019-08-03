package com.hphz.test;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileOrderTask implements Runnable {

    private Holder holder;
    private int order;
    public VolatileOrderTask(Holder holder, int order) {
        this.holder = holder;
        this.order = order;
    }

    @Override
    public void run(){
        if (holder.count % 3 == order) {
            System.out.println(Thread.currentThread().getName() + " ===== "+ order);
            System.out.println("holder.count: "+order);
            holder.count ++;
        }
    }
}
