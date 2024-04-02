package dSandAPortfolio;

import java.util.Queue;
import java.text.DecimalFormat;

class Display {
    private Order.OrderDetails[] ordersByName;
    private Order.OrderDetails[] ordersByNumber;
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public void update(Order order) {
        updateOrders(order.getOrderQueue());
    }

    private void updateOrders(Queue<Order.OrderDetails> orderQueue) {
        ordersByName = orderQueue.toArray(new Order.OrderDetails[orderQueue.size()]);
        ordersByNumber = orderQueue.toArray(new Order.OrderDetails[orderQueue.size()]);
        sortOrdersByName();
        sortOrdersByNumber();
        displayOrdersByName();
        displayOrdersByNumber();
    }

    private void sortOrdersByName() {
        quickSortByName(ordersByName, 0, ordersByName.length - 1);
    }

    private void sortOrdersByNumber() {
        quickSortByNumber(ordersByNumber, 0, ordersByNumber.length - 1);
    }

    private void quickSortByName(Order.OrderDetails[] orders, int low, int high) {
        if (low < high) {
            int partitionIndex = partitionByName(orders, low, high);
            quickSortByName(orders, low, partitionIndex - 1);
            quickSortByName(orders, partitionIndex + 1, high);
        }
    }

    private int partitionByName(Order.OrderDetails[] orders, int low, int high) {
        Order.OrderDetails pivot = orders[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (orders[j].getCustomerLastName().compareToIgnoreCase(pivot.getCustomerLastName()) >= 0) {
                i++;
                swap(orders, i, j);
            }
        }

        swap(orders, i + 1, high);
        return i + 1;
    }

    private void quickSortByNumber(Order.OrderDetails[] orders, int low, int high) {
        if (low < high) {
            int partitionIndex = partitionByNumber(orders, low, high);
            quickSortByNumber(orders, low, partitionIndex - 1);
            quickSortByNumber(orders, partitionIndex + 1, high);
        }
    }

    private int partitionByNumber(Order.OrderDetails[] orders, int low, int high) {
        Order.OrderDetails pivot = orders[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (orders[j].getOrderNumber() >= pivot.getOrderNumber()) {
                i++;
                swap(orders, i, j);
            }
        }

        swap(orders, i + 1, high);
        return i + 1;
    }

    private void swap(Order.OrderDetails[] orders, int i, int j) {
        Order.OrderDetails temp = orders[i];
        orders[i] = orders[j];
        orders[j] = temp;
    }

    public void displayOrdersByName() {
        System.out.println("Orders sorted by customer last name (descending):");
        for (Order.OrderDetails order : ordersByName) {
            System.out.println("Customer: " + order.getCustomerLastName() +
                    ", Order Number: " + order.getOrderNumber() +
                    ", Total Cost: $" + decimalFormat.format(order.getOrderTotalCost()));
        }
        System.out.println();
    }

    public void displayOrdersByNumber() {
        System.out.println("Orders sorted by order number (descending):");
        for (Order.OrderDetails order : ordersByNumber) {
            System.out.println("Order Number: " + order.getOrderNumber() +
                    ", Customer: " + order.getCustomerLastName() +
                    ", Total Cost: $" + decimalFormat.format(order.getOrderTotalCost()));
        }
        System.out.println();
    }
}