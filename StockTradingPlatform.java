import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Stock class
class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

// User class
class User {
    double balance = 10000; // starting money
    Map<String, Integer> portfolio = new HashMap<>();
}

// Main class
public class StockTradingPlatform {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Available stocks
        Map<String, Stock> market = new HashMap<>();
        market.put("TCS", new Stock("TCS", 3500));
        market.put("INFY", new Stock("INFY", 1500));
        market.put("RELIANCE", new Stock("RELIANCE", 2500));

        User user = new User();

        int choice;

        do {
            System.out.println("\n===== Stock Trading Platform =====");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\nAvailable Stocks:");
                    for (Stock s : market.values()) {
                        System.out.println(s.name + " : ₹" + s.price);
                    }
                    break;

                case 2:
                    System.out.print("Enter stock name: ");
                    String buyName = sc.next().toUpperCase();

                    if (market.containsKey(buyName)) {
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();

                        double cost = market.get(buyName).price * qty;

                        if (user.balance >= cost) {
                            user.balance -= cost;
                            user.portfolio.put(buyName,
                                    user.portfolio.getOrDefault(buyName, 0) + qty);

                            System.out.println("Stock bought successfully!");
                        } else {
                            System.out.println("Insufficient balance!");
                        }
                    } else {
                        System.out.println("Stock not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter stock name: ");
                    String sellName = sc.next().toUpperCase();

                    if (user.portfolio.containsKey(sellName)) {
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();

                        int owned = user.portfolio.get(sellName);

                        if (qty <= owned) {
                            double revenue = market.get(sellName).price * qty;
                            user.balance += revenue;

                            user.portfolio.put(sellName, owned - qty);

                            System.out.println("Stock sold successfully!");
                        } else {
                            System.out.println("Not enough stock to sell!");
                        }
                    } else {
                        System.out.println("You don't own this stock!");
                    }
                    break;

                case 4:
                    System.out.println("\n===== Portfolio =====");
                    System.out.println("Balance: ₹" + user.balance);

                    if (user.portfolio.isEmpty()) {
                        System.out.println("No stocks owned.");
                    } else {
                        for (String stock : user.portfolio.keySet()) {
                            System.out.println(stock + " : " + user.portfolio.get(stock));
                        }
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}