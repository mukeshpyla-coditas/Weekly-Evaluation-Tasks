package Week4.emailsender;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
    Virtual Threads are:
    => very light in weight(as these are managed by JVM rather than the OS)
    => Millions of virtual threads can be created at a time
    => Hence, scalability of virtual threads is high.
*/

public class EmailSenderViaVirtualThreads {
    public void sendEmails() {
        ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();
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

        long end = System.currentTimeMillis();

        System.out.println("Execution time of Virtual Threads to send 10000 emails(in milliSeconds): " + (end - start));
    }
}
