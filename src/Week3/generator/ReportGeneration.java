package Week3.generator;

import Week3.report.Report;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
    => Executor Service:
    A java.util.concurrent.ExecutorService interface is a subinterface of Executor interface,
    and adds features to manage the lifecycle, both of the individual tasks and of the executor itself.

    => CountDownLatch:
    A synchronization aid that allows one or more threads to wait until a set of operations
    being performed in other threads completes.
*/

public class ReportGeneration {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(8);
        int noOfTasks = 8;
        for(int i = 1; i <= noOfTasks; i++) {
            executorService.execute(new Report(latch));
        }

        latch.await();
        executorService.shutdown();

        System.out.println("All the 8 reports are successfully executed!");
        System.out.println("Shutting down the program...");
    }
}
