package Week4.emailsender;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
    Platform Threads are:
    => Heavy in terms of memory
    => There is a limit for how many platform threads can be created simultaneously - around few thousands
    => Scalability is low(as we cannot create more platform threads once - gives OutOfMemoryError)
*/

public class EmailSenderViaPlatformThreads {
    public void sendEmails() {
        ExecutorService service = Executors.newFixedThreadPool(100);
        long start = System.currentTimeMillis();

        for(int i=0; i<10000; i++) {
            service.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException e) {
                    System.out.println("Interrupted Exception occurred!");
                }
            });
        }

        service.shutdown();
        try {
            service.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long end = System.currentTimeMillis();

        System.out.println("Execution Time of Platform Threads for sending 10000 mails(in milliSeconds): " + (end - start));
    }
}
