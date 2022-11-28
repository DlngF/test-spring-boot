package com.example.demo2.controller;

import java.util.concurrent.atomic.AtomicInteger;

public class TestController {
    public static void main(String[] args) {

        AtomicInteger state = new AtomicInteger(1);
        AtomicInteger lun = new AtomicInteger(0);
        int slot = 10;
        for (int i = 1; i <= slot; i++) {
            new Thread(new Task(state,lun,i,slot,1000)).start();
        }
    }

}


class Task implements Runnable {

    public Task(AtomicInteger state,AtomicInteger lun,Integer start, Integer slot, Integer end) {
        this.state = state;
        this.lun = lun;
        this.start = start;
        this.slot = slot;
        this.end = end;
    }

    /**
     * 共享变量
     */
    private AtomicInteger state;

    /**
     * 轮次
     */
    private AtomicInteger lun ;

    /**
     * 几号
     * 1
     */
    private Integer start;

    /**
     * 几个线程
     * 3
     */
    private Integer slot;

    /**
     * 结束
     * 1000
     */
    private Integer end;

    @Override
    public void run() {
        while(true){
            if (state.get() == start) {
                // 1 4 7
                int i = start + (slot.intValue()) * lun.intValue();
                if(i > end){
                  return;
                }
                System.out.println(Thread.currentThread().getName() +": "+ i);
                if (state.get() == slot) {
                    lun.addAndGet(1);
                }
                int i1 = (state.get() + 1) % slot;
                state.set( i1 == 0 ? slot : i1);

            }
        }
    }
}
