package com.aeroncookbook.agrona.mysmiple;

import org.agrona.concurrent.AgentRunner;
import org.agrona.concurrent.CompositeAgent;
import org.agrona.concurrent.ShutdownSignalBarrier;

public class Main {
    public static void main(String[] args) {
        ShutdownSignalBarrier barrier1 = new ShutdownSignalBarrier();
        ShutdownSignalBarrier barrier2 = new ShutdownSignalBarrier();
        ShutdownSignalBarrier barrier3 = new ShutdownSignalBarrier();
        CompositeAgent compositeAgent = new CompositeAgent(new Sample1(barrier1), new Sample1(barrier2), new Sample1(barrier3));
        AgentRunner agentRunner = new AgentRunner(new MyIdleStrategy(), Throwable::printStackTrace, null, compositeAgent);
        AgentRunner.startOnThread(agentRunner);

        barrier1.await();
        barrier2.await();
        barrier3.await();

        agentRunner.close();
    }
}
