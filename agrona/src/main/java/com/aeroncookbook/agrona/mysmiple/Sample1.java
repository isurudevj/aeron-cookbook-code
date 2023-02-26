package com.aeroncookbook.agrona.mysmiple;

import org.agrona.concurrent.Agent;
import org.agrona.concurrent.ShutdownSignalBarrier;

public class Sample1 implements Agent {

    private int counter = 0;
    private ShutdownSignalBarrier barrier;

    public Sample1(ShutdownSignalBarrier barrier) {

        this.barrier = barrier;
    }

    @Override
    public int doWork() throws Exception {
        if (counter == 10_000) {
            barrier.signal();
        }
        counter++;
        return counter % 1000;
    }

    @Override
    public String roleName() {
        return "Connection Tester";
    }
}
