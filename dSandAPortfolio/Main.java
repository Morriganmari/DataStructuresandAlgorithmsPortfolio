package dSandAPortfolio;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Order order = new Order();
    private static Scanner scanner = new Scanner(System.in);
    private static Display display;

    public static void main(String[] args) {
        boolean exit = false;

        display = new Display();

        while (!exit) {
            displayMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    addOrder();
                    break;
                case 2:
                    removeOrder();
                    break;
                case 3:
                    displayOrders();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Order System Menu:");
        System.out.println("1. Add an order");
        System.out.println("2. Remove an order");
        System.out.println("3. Display orders");
        System.out.println("4. Exit");
        System.out.print("ENTER YOUR CHOICE (1-4): ");
    }

    private static int getChoice() {
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number option (1-4).");
                scanner.nextLine();
            }
        }
    }

    private static void addOrder() {
        String customerLastName = "";
        boolean validLastName = false;
        while (!validLastName) {
            System.out.print("Enter customer last name: ");
            customerLastName = scanner.next();
            if (isValidLastName(customerLastName)) {
                validLastName = true;
            } else {
                System.out.println("Invalid input. Please enter a valid last name (only letters, spaces, hyphens, and apostrophes are allowed).");
            }
        }

        int orderNumber = 0;
        boolean validOrderNumber = false;
        while (!validOrderNumber) {
            System.out.print("Enter order number: ");
            try {
                orderNumber = scanner.nextInt();
                validOrderNumber = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid order number (only integers are allowed).");
                scanner.nextLine(); 
            }
        }

        double orderTotalCost = 0;
        boolean validTotalCost = false;
        while (!validTotalCost) {
            System.out.print("Enter the order's total cost: $");
            try {
                orderTotalCost = scanner.nextDouble();
                validTotalCost = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid dollar amount for the order total cost.");
                scanner.nextLine(); 
            }
        }

        order.addOrder(customerLastName, orderNumber, orderTotalCost);
        display.update(order); 

        System.out.println("The order has been added successfully.");
        System.out.println();
    }

    private static boolean isValidLastName(String lastName) {
        String pattern = "^[a-zA-Z\\s'-]+$";
        return lastName.matches(pattern);
    }

    private static void removeOrder() {
        if (!order.isEmpty()) {
            Order.OrderDetails removedOrder = order.removeOrder();
            display.update(order); 
            System.out.println("Removed order:");
            System.out.println("Customer: " + removedOrder.getCustomerLastName() +
                    ", Order Number: " + removedOrder.getOrderNumber() +
                    ", Total Cost: $" + String.format("%.2f", removedOrder.getOrderTotalCost()));
            System.out.println();
        } else {
            System.out.println("There are no orders to remove and no changes have been made.");
            System.out.println();
        }
    }

    private static void displayOrders() {
        if (!order.isEmpty()) {
            System.out.println("Current orders:");
            display.displayOrdersByName();
            display.displayOrdersByNumber();
        } else {
            System.out.println("There are no orders to display at the moment as the order queue is empty.");
            System.out.println();
        }
    }
}

