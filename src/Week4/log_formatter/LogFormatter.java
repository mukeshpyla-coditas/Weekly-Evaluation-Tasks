package Week4.log_formatter;

// We use """ ... """ syntax for text blocks as shown below
public class LogFormatter {
    public static void main(String[] args) {
        String user = "MUKESH";
        String status = "SUCCESS";

        String fromattedString = """
                ===================================
                Application Log
                User: %s
                Status:%s
                ===================================""".formatted(user, status);

        System.out.println(fromattedString);
    }
}
