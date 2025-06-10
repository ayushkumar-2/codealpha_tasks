import java.util.*;

class Stock {
    String symbol;
    double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    double balance = 10000.0; // Initial balance

    public void buyStock(String symbol, int quantity, double price) {
        double totalCost = price * quantity;
        if (balance >= totalCost) {
            holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
            balance -= totalCost;
            System.out.println("Bought " + quantity + " shares of " + symbol);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void sellStock(String symbol, int quantity, double price) {
        int owned = holdings.getOrDefault(symbol, 0);
        if (owned >= quantity) {
            holdings.put(symbol, owned - quantity);
            balance += price * quantity;
            System.out.println("Sold " + quantity + " shares of " + symbol);
        } else {
            System.out.println("You do not own enough shares to sell.");
        }
    }

    public void viewPortfolio() {
        System.out.println("\n--- Portfolio ---");
        System.out.println("Balance: $" + balance);
        if (holdings.isEmpty()) {
            System.out.println("No holdings.");
        } else {
            for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " shares");
            }
        }
    }
}

class StockTradingPlatform {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Stock> market = new HashMap<>();
    private static Portfolio portfolio = new Portfolio();

    public static void main(String[] args) {
        initializeMarket();
        int choice;

        do {
            System.out.println("\n==== Stock Trading Platform ====");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewMarket();
                    break;
                case 2:
                    tradeStock(true);
                    break;
                case 3:
                    tradeStock(false);
                    break;
                case 4:
                    portfolio.viewPortfolio();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    private static void initializeMarket() {
        market.put("AAPL", new Stock("AAPL", 150.00));
        market.put("GOOG", new Stock("GOOG", 2800.00));
        market.put("TSLA", new Stock("TSLA", 720.00));
        market.put("AMZN", new Stock("AMZN", 3300.00));
    }

    private static void viewMarket() {
        System.out.println("\n--- Market Data ---");
        for (Stock stock : market.values()) {
            System.out.println(stock.symbol + " - $" + stock.price);
        }
    }

    private static void tradeStock(boolean isBuy) {
        System.out.print("Enter stock symbol: ");
        String symbol = scanner.nextLine().toUpperCase();

        if (!market.containsKey(symbol)) {
            System.out.println("Stock not found.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        double price = market.get(symbol).price;
        if (isBuy) {
            portfolio.buyStock(symbol, quantity, price);
        } else {
            portfolio.sellStock(symbol, quantity, price);
        }
    }
}
