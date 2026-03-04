package Week3.report;

import java.util.concurrent.CountDownLatch;

public class Report implements Runnable {
    CountDownLatch latch;
    static volatile int counter = 1;

    public Report(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Processor-" + Thread.currentThread().getName().charAt(Thread.currentThread().getName().length() - 1) +
                " is executing...");
        latch.countDown();
        System.out.println("Processing the report: Report-" + counter++);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Report generation completed...");
        System.out.println("Processor-" + Thread.currentThread().getName().charAt(Thread.currentThread().getName().length() - 1) +
                " is exiting...");
    }
}
