package Week4.emailsender;


/*
    The difference in the execution time of platform threads and virtual threads is shown below.
    From this we can conclude that, execution of tasks via virtual threads are faster compared to platform threads.
*/
public class EmailSender {
    public static void main(String[] args) {
        EmailSenderViaPlatformThreads platformThreads = new EmailSenderViaPlatformThreads();
        EmailSenderViaVirtualThreads virtualThreads = new EmailSenderViaVirtualThreads();

        platformThreads.sendEmails();
        virtualThreads.sendEmails();
    }
}
