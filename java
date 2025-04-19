import java.util.ArrayList;
import java.util.Scanner;

public class DailyExpenseTracker {
    // Expense Class
    static class Expense {
        private String category;
        private double amount;
        private String date; // Changed from LocalDate to String

        public Expense(String category, double amount, String date) {
            this.category = category;
            this.amount = amount;
            this.date = date;
        }

        public String getCategory() {
            return category;
        }

        public double getAmount() {
            return amount;
        }

        public String getDate() { // Returns string instead of LocalDate
            return date;
        }

        @Override
        public String toString() {
            return "Category: " + category + ", Amount: " + amount + ", Date: " + date;
        }
    }

    // ExpenseManager Class
    static class ExpenseManager {
        private ArrayList<Expense> expenses;

        public ExpenseManager() {
            expenses = new ArrayList<>();
        }

        public void addExpense(String category, double amount, String date) {
            Expense newExpense = new Expense(category, amount, date);
            expenses.add(newExpense);
        }

        public void viewExpenses() {
            if (expenses.isEmpty()) {
                System.out.println("No expenses recorded.");
            } else {
                for (Expense expense : expenses) {
                    System.out.println(expense);
                }
            }
        }

        public double calculateTotal() {
            double total = 0;
            for (Expense expense : expenses) {
                total += expense.getAmount();
            }
            return total;
        }
    }

    // Main Method
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Daily Expense Tracker ===");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Calculate Total Expenses");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice;
            while (true) {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                    scanner.next(); // Consume invalid input
                }
            }

            scanner.nextLine(); // Consume leftover newline

            switch (choice) {
                case 1:
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    
                    double amount;
                    while (true) {
                        System.out.print("Enter amount: ");
                        if (scanner.hasNextDouble()) {
                            amount = scanner.nextDouble();
                            if (amount > 0) break;
                            else System.out.println("Amount must be positive.");
                        } else {
                            System.out.println("Invalid amount. Please enter a valid number.");
                        }
                        scanner.next(); // Consume invalid input
                    }

                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String date = scanner.nextLine(); // Keeping date as string

                    manager.addExpense(category, amount, date);
                    System.out.println("Expense added!");
                    break;
                case 2:
                    manager.viewExpenses();
                    break;
                case 3:
                    System.out.printf("Total Expenses: %.2f\n", manager.calculateTotal());
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
