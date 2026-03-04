package Week4.orders;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Record classes are a better way of abstraction provided. By just defining the class signature
// with required instance variables(as shown below), we get getters() and toString() implicitly implemented.
record Order(Integer id, String customerName, Double amount, String type) { }

public class Task1 {

    public static void main(String[] args) {
        List<Order> listOfOrders = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        boolean choice = true;

        System.out.println("Enter the customer details as asked below: ");
        while(choice) {
            System.out.print("Enter customer Id: ");
            int customerId = s.nextInt();
            s.nextLine();

            System.out.print("Enter customer name: ");
            String customerName = s.nextLine();

            System.out.print("Enter order amount: ");
            double amount = s.nextDouble();
            s.nextLine();

            System.out.print("Enter shopping type: ");
            String shoppingType = s.nextLine();

            Order order = new Order(customerId, customerName, amount, shoppingType);

            listOfOrders.add(order);

            System.out.print("Do you want to enter more customer details?(1 -> YES ; 0 -> NO): ");
            int val = s.nextInt();

            if(val == 0) choice = false;
        }

        double totalRevenue = getTotalRevenue(listOfOrders);
        List<Order> premiumOrders = getPremiumOrders(listOfOrders);

        System.out.println("Total Revenue of Orders: " + totalRevenue);
        System.out.println("List of Premium Orders: " + premiumOrders);
        System.out.println("Final amounts after applying discounts: ");
        for(int i = 0; i < listOfOrders.size(); i++) {
            System.out.println("Final discount of Order-" + (i + 1) + ": " + applyDiscount(listOfOrders.get(i)));
        }
    }

    private static double applyDiscount(Order order) {
        return switch (order.type()) {
            case "ONLINE" -> order.amount() * 0.9;
            case "OFFLINE" -> order.amount() * 0.95;
            default -> order.amount();
        };
    }

    private static List<Order> getPremiumOrders(List<Order> orderList) {
        return orderList.stream()
                .filter(order -> order.amount() > 5000)
                .toList();
    }

    private static double getTotalRevenue(List<Order> orderList) {
        return orderList.stream()
                .mapToDouble(Order::amount)
                .sum();
    }
}